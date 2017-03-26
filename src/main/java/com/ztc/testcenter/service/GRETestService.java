package com.ztc.testcenter.service;

import com.ztc.testcenter.domain.question.*;
import com.ztc.testcenter.domain.test.*;
import com.ztc.testcenter.repository.UserRepository;
import com.ztc.testcenter.repository.question.*;
import com.ztc.testcenter.repository.test.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by yubar on 2/28/17.
 */

@Service
@Transactional
public class GRETestService implements TestService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;
    private final TestTemplateRepository testTemplateRepository;
    private final SectionTemplateRepository sectionTemplateRepository;
    private final TestSectionRepository testSectionRepository;
    private final AnsweredQuestionRepository answeredQuestionRepository;
    private final Random random = new Random();

    private final DataInterpretationSetQuestionRepository dataInterpretationSetQuestionRepository;
    private final NumericQuestionRepository numericQuestionRepository;
    private final QuantitativeComparisonQuestionRepository quantitativeComparisonQuestionRepository;
    private final QuantitativeSingleAnswerQuestionRepository quantitativeSingleAnswerQuestionRepository;
    private final QuantitativeMultipleAnswerQuestionRepository quantitativeMultipleAnswerQuestionRepository;
    private final ReadingComprehensionQuestionRepository readingComprehensionQuestionRepository;
    private final SentenceEquivalenceQuestionRepository sentenceEquivalenceQuestionRepository;
    private final TextCompletionQuestionRepository textCompletionQuestionRepository;
    private final WritingQuestionRepository writingQuestionRepository;

    @Autowired
    public GRETestService(UserRepository userRepository, QuestionRepository questionRepository, TestRepository testRepository, TestTemplateRepository testTemplateRepository, SectionTemplateRepository sectionTemplateRepository, TestSectionRepository testSectionRepository, AnsweredQuestionRepository answeredQuestionRepository, DataInterpretationSetQuestionRepository dataInterpretationSetQuestionRepository, NumericQuestionRepository numericQuestionRepository, QuantitativeComparisonQuestionRepository quantitativeComparisonQuestionRepository, QuantitativeSingleAnswerQuestionRepository quantitativeSingleAnswerQuestionRepository, QuantitativeMultipleAnswerQuestionRepository quantitativeMultipleAnswerQuestionRepository, ReadingComprehensionQuestionRepository readingComprehensionQuestionRepository, SentenceEquivalenceQuestionRepository sentenceEquivalenceQuestionRepository, TextCompletionQuestionRepository textCompletionQuestionRepository, WritingQuestionRepository writingQuestionRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.testRepository = testRepository;
        this.testTemplateRepository = testTemplateRepository;
        this.sectionTemplateRepository = sectionTemplateRepository;
        this.testSectionRepository = testSectionRepository;
        this.answeredQuestionRepository = answeredQuestionRepository;
        this.dataInterpretationSetQuestionRepository = dataInterpretationSetQuestionRepository;
        this.numericQuestionRepository = numericQuestionRepository;
        this.quantitativeComparisonQuestionRepository = quantitativeComparisonQuestionRepository;
        this.quantitativeSingleAnswerQuestionRepository = quantitativeSingleAnswerQuestionRepository;
        this.quantitativeMultipleAnswerQuestionRepository = quantitativeMultipleAnswerQuestionRepository;
        this.readingComprehensionQuestionRepository = readingComprehensionQuestionRepository;
        this.sentenceEquivalenceQuestionRepository = sentenceEquivalenceQuestionRepository;
        this.textCompletionQuestionRepository = textCompletionQuestionRepository;
        this.writingQuestionRepository = writingQuestionRepository;
    }

    public Test createTest(Test test) {
        test.setType(Test.Type.GRE);
        test.setDate(new Date());
        test.setTemplate(findTestTemplate());
        testRepository.save(test);
        TestSection firstSection = createTestSection(test);
        test.getTestSections().add(firstSection);
        return test;
    }

    public TestSection createTestSection(Test test) {
        SectionTemplate sectionTemplate = findSectionTemplate(test);
        TestSection testSection = new TestSection();
        testSection.setTest(test);
        testSection.setTemplate(sectionTemplate);
        testSection.setNumber(findSectionNumber(test));
        testSectionRepository.save(testSection);
        fillTestSection(testSection);
        return testSection;
    }

    public void answerQuestion(Long answeredQuestionId, String answer) {
        AnsweredQuestion answeredQuestion = answeredQuestionRepository.findOne(answeredQuestionId);
        answeredQuestion.setUserAnswer(answer);
    }

    private TestTemplate findTestTemplate() {
        List<TestTemplate> testTemplates = testTemplateRepository.findAll();
        return testTemplates.get(random.nextInt(testTemplates.size()));
    }

    private SectionTemplate findSectionTemplate(Test test) {
        if (test.getTestSections().size() == test.getTemplate().getItems().size())
            throw new IllegalArgumentException("All Test Sections for this test has been created");
        TestTemplateItem testTemplateItem = test.getTemplate().getItems().get(test.getTestSections().size());
        List<SectionTemplate> sectionTemplates = sectionTemplateRepository.findBySectionTypeAndDifficulty(testTemplateItem.getSectionType(), findSectionDifficulty(test));
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
                candidateQuestionsIds = testRepository.findQuestionIdForTest(testSection.getTest().getUser(), testSection.getTemplate().getDifficulty(), item.getDifficulty(), item.getQuestionType(), pageRequest);
            } else {
                Long countOfQuestions = questionRepository.countOfQuestions(item.getQuestionTemplate(), testSection.getTemplate().getDifficulty());
                Long countOfUserQuestions = questionRepository.countOfQuestions(testSection.getTest().getUser(), item.getQuestionTemplate(), testSection.getTemplate().getDifficulty());
                PageRequest pageRequest = new PageRequest(random.nextInt((int) ((countOfQuestions - countOfUserQuestions)/10)), 10);
                candidateQuestionsIds = testRepository.findQuestionIdForTest(testSection.getTest().getUser(), testSection.getTest().getDifficulty(), item.getQuestionTemplate(), pageRequest);
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
                    AnsweredQuestion answeredQuestion = new AnsweredQuestion(innerQuestion);
                    answeredQuestion.setNumber(item.getNumber() + ((InnerQuestion)innerQuestion).getNumber() - 1);
                    answeredQuestion.setTestSection(testSection);
                    answeredQuestion.setUser(testSection.getTest().getUser());
                    answeredQuestionRepository.save(answeredQuestion);
                    testSection.getAnsweredQuestions().add(answeredQuestion);
                });
            } else {
                AnsweredQuestion answeredQuestion = new AnsweredQuestion(question);
                answeredQuestion.setNumber(item.getNumber());
                answeredQuestion.setTestSection(testSection);
                answeredQuestion.setUser(testSection.getTest().getUser());
                answeredQuestionRepository.save(answeredQuestion);
                testSection.getAnsweredQuestions().add(answeredQuestion);
            }
        });
    }

    private Question findQuestion(Long id, QuestionType questionType) {
        switch (questionType) {
            case GRE_DATA_INTERPRETATION_SET:
                return dataInterpretationSetQuestionRepository.findOne(id);
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
