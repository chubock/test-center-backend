package com.ztc.testcenter.service;

import com.ztc.testcenter.domain.user.User;
import com.ztc.testcenter.domain.question.*;
import com.ztc.testcenter.domain.test.*;
import com.ztc.testcenter.repository.question.*;
import com.ztc.testcenter.repository.test.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import org.slf4j.Logger;

/**
 * Created by yubar on 2/28/17.
 */

@Service
@Transactional
public class GRETestService implements TestService {


    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;
    private final TestTemplateRepository testTemplateRepository;
    private final SectionTemplateRepository sectionTemplateRepository;
    private final TestSectionRepository testSectionRepository;
    private final AnsweredQuestionRepository answeredQuestionRepository;
    private final Random random = new Random();

    private final DataInterpretationSetQuestionRepository dataInterpretationSetQuestionRepository;
    private final DataInterpretationSingleAnswerQuestionRepository dataInterpretationSingleAnswerQuestionRepository;
    private final DataInterpretationMultipleAnswerQuestionRepository dataInterpretationMultipleAnswerQuestionRepository;
    private final DataInterpretationNumericQuestionRepository dataInterpretationNumericQuestionRepository;
    private final NumericQuestionRepository numericQuestionRepository;
    private final QuantitativeComparisonQuestionRepository quantitativeComparisonQuestionRepository;
    private final QuantitativeSingleAnswerQuestionRepository quantitativeSingleAnswerQuestionRepository;
    private final QuantitativeMultipleAnswerQuestionRepository quantitativeMultipleAnswerQuestionRepository;
    private final ReadingComprehensionQuestionRepository readingComprehensionQuestionRepository;
    private final ReadingComprehensionSingleAnswerQuestionRepository readingComprehensionSingleAnswerQuestionRepository;
    private final ReadingComprehensionMultipleAnswerQuestionRepository readingComprehensionMultipleAnswerQuestionRepository;
    private final SelectInPassageQuestionRepository selectInPassageQuestionRepository;
    private final SentenceEquivalenceQuestionRepository sentenceEquivalenceQuestionRepository;
    private final TextCompletionQuestionRepository textCompletionQuestionRepository;
    private final WritingQuestionRepository writingQuestionRepository;

    @Autowired
    public GRETestService(QuestionRepository questionRepository, TestRepository testRepository, TestTemplateRepository testTemplateRepository, SectionTemplateRepository sectionTemplateRepository, TestSectionRepository testSectionRepository, AnsweredQuestionRepository answeredQuestionRepository, DataInterpretationSetQuestionRepository dataInterpretationSetQuestionRepository, DataInterpretationSingleAnswerQuestionRepository dataInterpretationSingleAnswerQuestionRepository, DataInterpretationMultipleAnswerQuestionRepository dataInterpretationMultipleAnswerQuestionRepository, DataInterpretationNumericQuestionRepository dataInterpretationNumericQuestionRepository, NumericQuestionRepository numericQuestionRepository, QuantitativeComparisonQuestionRepository quantitativeComparisonQuestionRepository, QuantitativeSingleAnswerQuestionRepository quantitativeSingleAnswerQuestionRepository, QuantitativeMultipleAnswerQuestionRepository quantitativeMultipleAnswerQuestionRepository, ReadingComprehensionQuestionRepository readingComprehensionQuestionRepository, ReadingComprehensionSingleAnswerQuestionRepository readingComprehensionSingleAnswerQuestionRepository, ReadingComprehensionMultipleAnswerQuestionRepository readingComprehensionMultipleAnswerQuestionRepository, SelectInPassageQuestionRepository selectInPassageQuestionRepository, SentenceEquivalenceQuestionRepository sentenceEquivalenceQuestionRepository, TextCompletionQuestionRepository textCompletionQuestionRepository, WritingQuestionRepository writingQuestionRepository) {
        this.questionRepository = questionRepository;
        this.testRepository = testRepository;
        this.testTemplateRepository = testTemplateRepository;
        this.sectionTemplateRepository = sectionTemplateRepository;
        this.testSectionRepository = testSectionRepository;
        this.answeredQuestionRepository = answeredQuestionRepository;
        this.dataInterpretationSetQuestionRepository = dataInterpretationSetQuestionRepository;
        this.dataInterpretationSingleAnswerQuestionRepository = dataInterpretationSingleAnswerQuestionRepository;
        this.dataInterpretationMultipleAnswerQuestionRepository = dataInterpretationMultipleAnswerQuestionRepository;
        this.dataInterpretationNumericQuestionRepository = dataInterpretationNumericQuestionRepository;
        this.numericQuestionRepository = numericQuestionRepository;
        this.quantitativeComparisonQuestionRepository = quantitativeComparisonQuestionRepository;
        this.quantitativeSingleAnswerQuestionRepository = quantitativeSingleAnswerQuestionRepository;
        this.quantitativeMultipleAnswerQuestionRepository = quantitativeMultipleAnswerQuestionRepository;
        this.readingComprehensionQuestionRepository = readingComprehensionQuestionRepository;
        this.readingComprehensionSingleAnswerQuestionRepository = readingComprehensionSingleAnswerQuestionRepository;
        this.readingComprehensionMultipleAnswerQuestionRepository = readingComprehensionMultipleAnswerQuestionRepository;
        this.selectInPassageQuestionRepository = selectInPassageQuestionRepository;
        this.sentenceEquivalenceQuestionRepository = sentenceEquivalenceQuestionRepository;
        this.textCompletionQuestionRepository = textCompletionQuestionRepository;
        this.writingQuestionRepository = writingQuestionRepository;
    }

    public Test createTest(User user, Difficulty difficulty, Test.TestIntelligentType intelligentType, Boolean free) {
        if (free) {
            if (user.getFreeGreTestCount() == 0)
                throw new IllegalStateException();
            else
                user.decrementFreeGRETestCount();
        } else {
            if (user.getGreTestCount() == 0)
                throw new IllegalStateException();
            else
                user.decrementGreTestCount();
        }
        Test test = new Test(user, findTestTemplate(), free);
        test.setDifficulty(difficulty);
        test.setIntelligentType(intelligentType);
        testRepository.save(test);
        TestSection firstSection = createTestSection(test.getId());
        test.getTestSections().add(firstSection);
        return test;
    }

    public TestSection createTestSection(Long testId) {
        Test test = testRepository.findOne(testId);
        SectionTemplate sectionTemplate = findSectionTemplate(test);
        TestSection testSection = new TestSection(test, sectionTemplate);
        if (! test.getTestSections().isEmpty()) {
            TestSection lastSection = test.getTestSections().get(test.getTestSections().size() - 1);
            int allowedSectionTime = lastSection.getSectionType().time + lastSection.getSectionType().breakTime;
            if (lastSection.getStartDate().toInstant().isAfter(Instant.now().minusSeconds(allowedSectionTime * 60)))
                lastSection.setEndDate(new Date());
            else
                lastSection.setEndDate(Date.from(lastSection.getStartDate().toInstant().plusSeconds(allowedSectionTime)));
            testSectionRepository.save(lastSection);
            testSection.setStartDate(lastSection.getEndDate());
        } else {
            testSection.setStartDate(test.getStartDate());
        }
        testSection.setNumber(findSectionNumber(test));
        testSectionRepository.save(testSection);
        fillTestSection(testSection);
        return testSection;
    }

    public TestSection createTestSection(Long id, Map<Long, String> answers) {
        answerQuestions(answers);
        return createTestSection(id);
    }

    public void answerQuestion(Long answeredQuestionId, String answer) {
        AnsweredQuestion answeredQuestion = answeredQuestionRepository.findOne(answeredQuestionId);
        //check if test section is not finished.
        if (answeredQuestion.getTestSection().getEndDate() != null)
            throw new IllegalStateException();
        int time = answeredQuestion.getTestSection().getSectionType().breakTime + answeredQuestion.getTestSection().getSectionType().time + 1;
        //check if its not too late
//        if (answeredQuestion.getTestSection().getStartDate().toInstant().isBefore(Instant.now().minusSeconds(time * 60)))
//            throw new IllegalStateException();
        answeredQuestion.setUserAnswer(answer);
    }

    public Date finishTest(Long testId, Map<Long, String> answers) {
        answerQuestions(answers);
        Test test = testRepository.findOne(testId);
        TestSection lastSection = test.getTestSections().get(test.getTestSections().size() - 1);
        int allowedSectionTime = lastSection.getSectionType().time + lastSection.getSectionType().breakTime;
        if (lastSection.getStartDate().toInstant().isAfter(Instant.now().minusSeconds(allowedSectionTime * 60)))
            lastSection.setEndDate(new Date());
        else
            lastSection.setEndDate(Date.from(lastSection.getStartDate().toInstant().plusSeconds(allowedSectionTime)));
        test.setEndDate(lastSection.getEndDate());
        testRepository.save(test);
        testSectionRepository.save(lastSection);
        return test.getEndDate();
    }

    private void answerQuestions(Map<Long, String> answers) {
        answers.keySet().forEach(questionId -> answerQuestion(questionId, answers.get(questionId)));
    }

    private TestTemplate findTestTemplate() {
        List<TestTemplate> testTemplates = testTemplateRepository.findAll();
        return testTemplates.get(random.nextInt(testTemplates.size()));
    }

    private SectionTemplate findSectionTemplate(Test test) {
        if (test.getTestSections().size() == test.getTemplate().getItems().size())
            throw new IllegalArgumentException("All Test Sections for this test has been created");
        TestTemplateItem testTemplateItem = test.getTemplate().getItems().get(test.getTestSections().size());
        List<SectionTemplate> sectionTemplates = sectionTemplateRepository.findBySectionTypeAndDifficultyAndFree(testTemplateItem.getSectionType(), findSectionDifficulty(test), test.getFree());
        return sectionTemplates.get(random.nextInt(sectionTemplates.size()));
    }

    //TODO: This method should be implemented better later.
    private Difficulty findSectionDifficulty(Test test) {
        Difficulty sectionTemplateDifficulty = Difficulty.MEDIUM;
        if (test.getIntelligentType() == Test.TestIntelligentType.NORMAL)
            sectionTemplateDifficulty = test.getDifficulty();
        return sectionTemplateDifficulty;
    }

    private Integer findSectionNumber(Test test) {
        TestTemplateItem testTemplateItem = test.getTemplate().getItems().get(test.getTestSections().size());
        return testTemplateItem.getNumber();
    }

    private void fillTestSection(TestSection testSection) {
        List<Long> usedQuestionsIds = new ArrayList<>();
        testSection.getTemplate().getItems().forEach(item -> {
            List<Long> candidateQuestionsIds;
            if (item.getQuestionTemplate() == null) {
                Long countOfQuestions = questionRepository.countOfQuestions(item.getQuestionType(), testSection.getTemplate().getDifficulty(), item.getDifficulty());
                Long countOfUserQuestions = questionRepository.countOfQuestions(testSection.getTest().getUser(), item.getQuestionType(), testSection.getTemplate().getDifficulty(), item.getDifficulty());
                PageRequest pageRequest = new PageRequest(random.nextInt((int) ((countOfQuestions - countOfUserQuestions)/10)), 10);
                candidateQuestionsIds = testRepository.findQuestionIdForTest(testSection.getTest().getUser(), testSection.getTemplate().getDifficulty(), item.getDifficulty(), item.getQuestionType(), testSection.getTest().getFree(), pageRequest);
                //Check if we can't find unique question, then we have to find some duplicate questions;
                if (candidateQuestionsIds == null || candidateQuestionsIds.isEmpty())
                    candidateQuestionsIds = testRepository.findQuestionIdForTest(testSection.getTemplate().getDifficulty(), item.getDifficulty(), item.getQuestionType(), testSection.getTest().getFree(), new PageRequest(random.nextInt((int) (countOfQuestions/10)), 10));
            } else {
                Long countOfQuestions = questionRepository.countOfQuestions(item.getQuestionTemplate(), testSection.getTemplate().getDifficulty());
                Long countOfUserQuestions = questionRepository.countOfQuestions(testSection.getTest().getUser(), item.getQuestionTemplate(), testSection.getTemplate().getDifficulty());
                PageRequest pageRequest = new PageRequest(random.nextInt((int) ((countOfQuestions - countOfUserQuestions)/10)), 10);
                candidateQuestionsIds = testRepository.findQuestionIdForTest(testSection.getTest().getUser(), testSection.getTest().getDifficulty(), item.getQuestionTemplate(), testSection.getTest().getFree(), pageRequest);
                //Check if we can't find unique question, then we have to find some duplicate questions;
                if (candidateQuestionsIds == null || candidateQuestionsIds.isEmpty())
                    candidateQuestionsIds = testRepository.findQuestionIdForTest(testSection.getTest().getDifficulty(), item.getQuestionTemplate(), testSection.getTest().getFree(), new PageRequest(random.nextInt((int) (countOfQuestions/10)), 10));
            }
            if (candidateQuestionsIds == null || candidateQuestionsIds.isEmpty())
                throw new IllegalStateException("Can't find any question for this test");
            Long questionId = candidateQuestionsIds.get(random.nextInt(candidateQuestionsIds.size()));
            while(usedQuestionsIds.contains(questionId))
                questionId = candidateQuestionsIds.get(random.nextInt(candidateQuestionsIds.size()));
            usedQuestionsIds.add(questionId);
            Question question = findQuestion(questionId, item.getQuestionTemplate() == null ? item.getQuestionType() : item.getQuestionTemplate().getQuestionType());
            if (question instanceof QuestionsContainer) {
                QuestionsContainer questionsContainer = (QuestionsContainer) questionRepository.findOne(question.getId());
                questionsContainer.innerQuestions().forEach(innerQuestion -> {
                    AnsweredQuestion answeredQuestion = new AnsweredQuestion(testSection, innerQuestion);
                    answeredQuestion.setNumber(item.getNumber() + ((InnerQuestion)innerQuestion).getNumber() - 1);
                    answeredQuestion.setUser(testSection.getTest().getUser());
                    answeredQuestionRepository.save(answeredQuestion);
                    testSection.getAnsweredQuestions().add(answeredQuestion);
                });
            } else {
                AnsweredQuestion answeredQuestion = new AnsweredQuestion(testSection, question);
                answeredQuestion.setNumber(item.getNumber());
                answeredQuestion.setUser(testSection.getTest().getUser());
                answeredQuestionRepository.save(answeredQuestion);
                testSection.getAnsweredQuestions().add(answeredQuestion);
            }
        });
    }

    public Question findQuestion(Long id, QuestionType questionType) {
        switch (questionType) {
            case GRE_DATA_INTERPRETATION_SET:
                return dataInterpretationSetQuestionRepository.findOne(id);
            case GRE_DATA_INTERPRETATION_SET_SINGLE_ANSWER:
                return dataInterpretationSingleAnswerQuestionRepository.findOne(id);
            case GRE_DATA_INTERPRETATION_SET_MULTIPLE_ANSWER:
                return dataInterpretationMultipleAnswerQuestionRepository.findOne(id);
            case GRE_DATA_INTERPRETATION_SET_NUMERIC:
                return dataInterpretationNumericQuestionRepository.findOne(id);
            case GRE_NUMERIC:
            case GRE_NUMERIC_FRACTION:
                return numericQuestionRepository.findOne(id);
            case GRE_QUANTITATIVE_COMPARISON:
                return quantitativeComparisonQuestionRepository.findOne(id);
            case GRE_QUANTITATIVE_SINGLE_ANSWER:
                return quantitativeSingleAnswerQuestionRepository.findOne(id);
            case GRE_QUANTITATIVE_MULTIPLE_ANSWER:
                return quantitativeMultipleAnswerQuestionRepository.findOne(id);
            case GRE_READING_COMPREHENSION_SHORT:
            case GRE_READING_COMPREHENSION_MEDIUM:
            case GRE_READING_COMPREHENSION_LONG:
            case GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT:
                return readingComprehensionQuestionRepository.findOne(id);
            case GRE_READING_COMPREHENSION_SINGLE_ANSWER:
                return readingComprehensionSingleAnswerQuestionRepository.findOne(id);
            case GRE_READING_COMPREHENSION_MULTIPLE_ANSWER:
                return readingComprehensionMultipleAnswerQuestionRepository.findOne(id);
            case GRE_READING_COMPREHENSION_SELECT_IN_PASSAGE:
                return selectInPassageQuestionRepository.findOne(id);
            case GRE_SENTENCE_EQUIVALENCE:
                return sentenceEquivalenceQuestionRepository.findOne(id);
            case GRE_TEXT_COMPLETION_ONE_BLANK:
            case GRE_TEXT_COMPLETION_TWO_BLANK:
            case GRE_TEXT_COMPLETION_THREE_BLANK:
                return textCompletionQuestionRepository.findOne(id);
            case GRE_WRITING_ISSUE:
            case GRE_WRITING_ARGUMENT:
                return writingQuestionRepository.findOne(id);
            default:
                throw new IllegalArgumentException();
        }
    }
}
