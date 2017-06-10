package com.ztc.testcenter.service;

import com.ztc.testcenter.domain.user.User;
import com.ztc.testcenter.domain.question.*;
import com.ztc.testcenter.domain.test.*;
import com.ztc.testcenter.exceptions.*;
import com.ztc.testcenter.repository.question.*;
import com.ztc.testcenter.repository.test.*;
import com.ztc.testcenter.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import org.slf4j.Logger;

/**
 * Created by yubar on 2/28/17.
 */

@Service
@Transactional
public class GRETestService implements TestService {


    private final Logger logger;

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;
    private final TestTemplateRepository testTemplateRepository;
    private final SectionTemplateRepository sectionTemplateRepository;
    private final TestSectionRepository testSectionRepository;
    private final AnsweredQuestionRepository answeredQuestionRepository;
    private final Random random = new Random();

    private final QuestionService questionService;

    @Autowired
    public GRETestService(Logger logger, UserRepository userRepository, QuestionRepository questionRepository, TestRepository testRepository, TestTemplateRepository testTemplateRepository, SectionTemplateRepository sectionTemplateRepository, TestSectionRepository testSectionRepository, AnsweredQuestionRepository answeredQuestionRepository, QuestionService questionService) {
        this.logger = logger;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.testRepository = testRepository;
        this.testTemplateRepository = testTemplateRepository;
        this.sectionTemplateRepository = sectionTemplateRepository;
        this.testSectionRepository = testSectionRepository;
        this.answeredQuestionRepository = answeredQuestionRepository;
        this.questionService = questionService;
    }

    public Test createTest(User user, Difficulty difficulty, Test.TestIntelligentType intelligentType) {
        user = userRepository.findOne(user.getId());
        boolean freeTest = false;
        if (user.getFreeGreTestCount() > 0) {
            freeTest = true;
            user.decrementFreeGRETestCount();
        } else {
            if (user.getGreTestCount() == 0)
                throw new NoTestAvailableException();
            else
                user.decrementGreTestCount();
        }
        Test test = new Test(user, findTestTemplate(), freeTest);
        test.setDifficulty(difficulty);
        test.setIntelligentType(intelligentType);
        testRepository.save(test);
        TestSection firstSection = createTestSection(test.getId());
        test.getTestSections().add(firstSection);
        return test;
    }

    public TestSection createTestSection(Long testId) {
        Test test = testRepository.findOne(testId);
        if (test == null)
            throw new TestNotFoundException();
        SectionTemplate sectionTemplate = findSectionTemplate(test);
        TestSection testSection = new TestSection(test, sectionTemplate);
        if (! test.getTestSections().isEmpty()) {
            TestSection lastSection = test.getTestSections().get(test.getTestSections().size() - 1);
            lastSection.setEndDate(new Date());
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

    @Override
    public void seeQuestion(Long id) {
        AnsweredQuestion question = answeredQuestionRepository.findOne(id);
        question.setSeen();
        answeredQuestionRepository.save(question);
        updateLastActivityInfo(question);
    }

    public TestSection createTestSection(Long id, Map<Long, String> answers) {
        answerQuestions(answers);
        return createTestSection(id);
    }

    public void answerQuestion(Long answeredQuestionId, String answer) {
        answerQuestion(answeredQuestionId, answer, true);
    }

    @Override
    public void markQuestion(Long id) {
        AnsweredQuestion question = answeredQuestionRepository.findOne(id);
        question.setMarked(true);
        answeredQuestionRepository.save(question);
        updateLastActivityInfo(question);
    }

    @Override
    public void unMarkQuestion(Long id) {
        AnsweredQuestion question = answeredQuestionRepository.findOne(id);
        question.setMarked(false);
        answeredQuestionRepository.save(question);
        updateLastActivityInfo(question);
    }

    private void answerQuestion(Long answeredQuestionId, String answer, boolean updateLastActivity) {
        AnsweredQuestion answeredQuestion = answeredQuestionRepository.findOne(answeredQuestionId);
        if (answeredQuestion == null)
            throw new AnsweredQuestionNotFound();
        if (answeredQuestion.getTestSection().getEndDate() != null)
            throw new TestSectionTimeFinishedException();
        answeredQuestion.setUserAnswer(answer);
        if (answer == null || answer.equals(""))
            answeredQuestion.setStatus(AnsweredQuestion.Status.NOT_ANSWERED);
        else {
            Question question = questionService.findQuestion(answeredQuestion.getQuestion().getId(), answeredQuestion.getQuestionType());
            answeredQuestion.setStatus(question.isCorrect(answer) ? AnsweredQuestion.Status.CORRECT : AnsweredQuestion.Status.INCORRECT);
        }
        if (updateLastActivity)
            updateLastActivityInfo(answeredQuestion);
    }

    public Date finishTest(Long testId, Map<Long, String> answers) {
        Test test = testRepository.findOne(testId);
        if (test == null)
            throw new TestNotFoundException();
        answerQuestions(answers);
        TestSection lastSection = test.getTestSections().get(test.getTestSections().size() - 1);
        lastSection.setEndDate(new Date());
        test.setEndDate(lastSection.getEndDate());
        testRepository.save(test);
        testSectionRepository.save(lastSection);
        return test.getEndDate();
    }

    public void startSection(Long testSectionId) {
        testSectionRepository.findOne(testSectionId).setLastActivityDate(new Date());
    }

    private void answerQuestions(Map<Long, String> answers) {
        answers.keySet().forEach(questionId -> answerQuestion(questionId, answers.get(questionId), false));
    }

    private TestTemplate findTestTemplate() {
        List<TestTemplate> testTemplates = testTemplateRepository.findAll();
        if (testTemplates.size() == 0)
            throw new NoTestTemplateFound();
        return testTemplates.get(random.nextInt(testTemplates.size()));
    }

    private void updateLastActivityInfo(AnsweredQuestion answeredQuestion) {
        long questionElapsedTime = Duration.between(answeredQuestion.getTestSection().getLastActivityDate().toInstant(), Instant.now()).getSeconds();
        answeredQuestion.getTestSection().setRemainingSeconds(answeredQuestion.getTestSection().getRemainingSeconds() - questionElapsedTime);
        answeredQuestion.getTestSection().setLastQuestionNumber(answeredQuestion.getNumber());
        answeredQuestion.getTestSection().setLastActivityDate(new Date());
    }

    private SectionTemplate findSectionTemplate(Test test) {
        if (test.getTestSections().size() == test.getTemplate().getItems().size())
            throw new TestSectionsFinishedException();
        TestTemplateItem testTemplateItem = test.getTemplate().getItems().get(test.getTestSections().size());
        List<SectionTemplate> sectionTemplates = sectionTemplateRepository.findBySectionTypeAndDifficultyAndFree(testTemplateItem.getSectionType(), findSectionDifficulty(test), test.getFree());
        if (sectionTemplates.size() == 0)
            throw new NoSectionTemplateAvailable();
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
                Long countOfQuestions = questionRepository.countOfQuestions(item.getQuestionType(), testSection.getTemplate().getDifficulty(), item.getDifficulty(), testSection.getTest().getFree());
                Long countOfUserQuestions = questionRepository.countOfQuestions(testSection.getTest().getUser(), item.getQuestionType(), testSection.getTemplate().getDifficulty(), item.getDifficulty(), testSection.getTest().getFree());
                PageRequest pageRequest = getPageRequest(countOfQuestions, countOfUserQuestions);
                candidateQuestionsIds = testRepository.findQuestionIdForTest(testSection.getTest().getUser(), testSection.getTemplate().getDifficulty(), item.getDifficulty(), item.getQuestionType(), testSection.getTest().getFree(), pageRequest);
                //Check if we can't find unique question, then we have to find some questions anyway;
                if (candidateQuestionsIds == null || candidateQuestionsIds.isEmpty()){
                    logger.warn("Couldn't find unique question for user : " + testSection.getTest().getUser().getId() + ", difficulty: " + testSection.getTemplate().getDifficulty() + ", difficulty_level: " + item.getDifficulty() + ", free: " + testSection.getTest().getFree() + " (page_number: " + pageRequest.getPageNumber() + ", page_size: " + pageRequest.getPageSize());
                    candidateQuestionsIds = testRepository.findQuestionIdForTest(testSection.getTemplate().getDifficulty(), item.getDifficulty(), item.getQuestionType(), testSection.getTest().getFree(), new PageRequest(random.nextInt((int) (countOfQuestions/10)), 10));
                }
            } else {
                Long countOfQuestions = questionRepository.countOfQuestions(item.getQuestionTemplate(), testSection.getTemplate().getDifficulty(), testSection.getTest().getFree());
                Long countOfUserQuestions = questionRepository.countOfQuestions(testSection.getTest().getUser(), item.getQuestionTemplate(), testSection.getTemplate().getDifficulty(), testSection.getTest().getFree());
                PageRequest pageRequest = getPageRequest(countOfQuestions, countOfUserQuestions);
                candidateQuestionsIds = testRepository.findQuestionIdForTest(testSection.getTest().getUser(), testSection.getTest().getDifficulty(), item.getQuestionTemplate(), testSection.getTest().getFree(), pageRequest);
                //Check if we can't find unique question, then we have to find some questions anyway;
                if (candidateQuestionsIds == null || candidateQuestionsIds.isEmpty()){
                    logger.warn("Couldn't find unique question for user : " + testSection.getTest().getUser().getId() + ", difficulty: " + testSection.getTemplate().getDifficulty() + ", question_template: " + item.getQuestionTemplate().getLabel() + ", free: " + testSection.getTest().getFree() + " (page_number: " + pageRequest.getPageNumber() + ", page_size: " + pageRequest.getPageSize());
                    candidateQuestionsIds = testRepository.findQuestionIdForTest(testSection.getTest().getDifficulty(), item.getQuestionTemplate(), testSection.getTest().getFree(), new PageRequest(random.nextInt((int) (countOfQuestions/10)), 10));
                }
            }
            if (candidateQuestionsIds == null || candidateQuestionsIds.isEmpty())
                throw new NoQuestionAvailableException();
            Long questionId = candidateQuestionsIds.get(random.nextInt(candidateQuestionsIds.size()));
            while(usedQuestionsIds.contains(questionId))
                questionId = candidateQuestionsIds.get(random.nextInt(candidateQuestionsIds.size()));
            usedQuestionsIds.add(questionId);
            Question question = questionService.findQuestion(questionId, item.getQuestionTemplate() == null ? item.getQuestionType() : item.getQuestionTemplate().getQuestionType());
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

    private PageRequest getPageRequest(Long all, Long used) {
        if (all - used < 10)
            return new PageRequest(0, 10);
        return new PageRequest(random.nextInt((int) ((all - used)/10)), 10);
    }

}
