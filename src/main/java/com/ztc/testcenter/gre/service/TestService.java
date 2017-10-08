package com.ztc.testcenter.gre.service;

import com.ztc.testcenter.gre.domain.test.*;
import com.ztc.testcenter.gre.exceptions.*;
import com.ztc.testcenter.gre.repository.test.*;
import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.gre.domain.question.Difficulty;
import com.ztc.testcenter.gre.domain.question.InnerQuestion;
import com.ztc.testcenter.gre.domain.question.Question;
import com.ztc.testcenter.gre.domain.question.QuestionsContainer;
import com.ztc.testcenter.gre.repository.question.QuestionRepository;
import com.ztc.testcenter.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import org.slf4j.Logger;

/**
 * Created by yubar on 2/28/17.
 */

@Service
@Transactional
public class TestService {


    private final Logger logger;

    private final UserService userService;
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;
    private final TestTemplateRepository testTemplateRepository;
    private final SectionTemplateRepository sectionTemplateRepository;
    private final TestSectionRepository testSectionRepository;
    private final AnsweredQuestionRepository answeredQuestionRepository;
    private final Random random = new Random();

    private final QuestionService questionService;
    private final AnsweredQuestionService answeredQuestionService;

    private final TestTicketRepository testTicketRepository;

    @Autowired
    public TestService(Logger logger, UserService userService, QuestionRepository questionRepository, TestRepository testRepository, TestTemplateRepository testTemplateRepository, SectionTemplateRepository sectionTemplateRepository, TestSectionRepository testSectionRepository, AnsweredQuestionRepository answeredQuestionRepository, QuestionService questionService, AnsweredQuestionService answeredQuestionService, TestTicketRepository testTicketRepository) {
        this.logger = logger;
        this.userService = userService;
        this.questionRepository = questionRepository;
        this.testRepository = testRepository;
        this.testTemplateRepository = testTemplateRepository;
        this.sectionTemplateRepository = sectionTemplateRepository;
        this.testSectionRepository = testSectionRepository;
        this.answeredQuestionRepository = answeredQuestionRepository;
        this.questionService = questionService;
        this.answeredQuestionService = answeredQuestionService;
        this.testTicketRepository = testTicketRepository;
    }

    public List<Test> getUserTests(String username) {
        return testRepository.findByUsernameAndEndDateIsNotNull(username);
    }

    public Test getTest(Long testId) {
        return testRepository.findOneWithSectionsAndTemplate(testId);
    }

    public Test getUserCurrentTest(String username) {
        Long testId = testRepository.getIdByUsernameAndEndDateIsNull(username);
        return testId == null ? null : getTest(testId);
    }

    public Test createTest(String username, Difficulty difficulty) {

        TestTicket ticket = testTicketRepository.findFirstByUsernameAndFreeAndTestIsNull(username, true);
        boolean freeTest = true;

        if (ticket == null) {
            ticket = testTicketRepository.findFirstByUsernameAndFreeAndTestIsNull(username, false);
            freeTest = false;
        }

        if (ticket == null)
            throw new NoTestAvailableException();

        Test test = new Test(username, findTestTemplate(), freeTest);
        test.setDifficulty(difficulty);
        testRepository.save(test);

        ticket.setTest(test);

        TestSection firstSection = createTestSection(test.getId());
        test.getTestSections().add(firstSection);
        return test;
    }

    public TestSection getTestSection(Long id) {
        TestSection testSection = testSectionRepository.findOneWithQuestions(id);
        testSection
                .getAnsweredQuestions()
                .forEach(answeredQuestion ->
                        answeredQuestion.setQuestion(
                                questionService.findQuestion(
                                        answeredQuestion.getQuestion().getId(),
                                        answeredQuestion.getQuestionType()
                                )
                        )
                );
        return testSection;
    }

    private TestSection createTestSection(Long testId) {
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

    public TestSection createTestSection(Long id, Map<Long, String> answers) {
        answeredQuestionService.answerQuestions(answers);
        return createTestSection(id);
    }

    public Date finishTest(Long testId, Map<Long, String> answers) {
        Test test = testRepository.findOne(testId);
        if (test == null)
            throw new TestNotFoundException();
        answeredQuestionService.answerQuestions(answers);
        TestSection lastSection = test.getTestSections().get(test.getTestSections().size() - 1);
        lastSection.setEndDate(new Date());
        test.setEndDate(lastSection.getEndDate());
        testRepository.save(test);
        testSectionRepository.save(lastSection);
        return test.getEndDate();
    }

    public void commentTest(Long testId, String comment) {
        Test test = testRepository.getOne(testId);
        test.setComment(comment);
    }

    public void startSection(Long testSectionId) {
        testSectionRepository.findOne(testSectionId).setLastActivityDate(new Date());
    }

    private TestTemplate findTestTemplate() {
        List<TestTemplate> testTemplates = testTemplateRepository.findAll();
        if (testTemplates.size() == 0)
            throw new NoTestTemplateFoundException();
        return testTemplates.get(random.nextInt(testTemplates.size()));
    }

    private SectionTemplate findSectionTemplate(Test test) {
        if (test.getTestSections().size() == test.getTemplate().getItems().size())
            throw new TestSectionsFinishedException();
        TestTemplateItem testTemplateItem = test.getTemplate().getItems().get(test.getTestSections().size());
        List<SectionTemplate> sectionTemplates = sectionTemplateRepository.findBySectionTypeAndDifficultyAndFree(testTemplateItem.getSectionType(), findSectionDifficulty(test), test.getFree());
        if (sectionTemplates.size() == 0)
            throw new NoSectionTemplateAvailableException();
        return sectionTemplates.get(random.nextInt(sectionTemplates.size()));
    }

    //TODO: This method should be implemented better later.
    private Difficulty findSectionDifficulty(Test test) {
        return Difficulty.MEDIUM;
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
                Long countOfUserQuestions = questionRepository.countOfQuestions(testSection.getTest().getUsername(), item.getQuestionType(), testSection.getTemplate().getDifficulty(), item.getDifficulty(), testSection.getTest().getFree());
                PageRequest pageRequest = getPageRequest(countOfQuestions, countOfUserQuestions);
                candidateQuestionsIds = testRepository.findQuestionIdForTest(testSection.getTest().getUsername(), testSection.getTemplate().getDifficulty(), item.getDifficulty(), item.getQuestionType(), testSection.getTest().getFree(), pageRequest);
                //Check if we can't find unique gre, then we have to find some questions anyway;
                if (candidateQuestionsIds == null || candidateQuestionsIds.isEmpty()){
                    logger.warn("Couldn't find unique gre for user : " + testSection.getTest().getUsername() + ", difficulty: " + testSection.getTemplate().getDifficulty() + ", difficulty_level: " + item.getDifficulty() + ", free: " + testSection.getTest().getFree() + " (page_number: " + pageRequest.getPageNumber() + ", page_size: " + pageRequest.getPageSize());
                    candidateQuestionsIds = testRepository.findQuestionIdForTest(testSection.getTemplate().getDifficulty(), item.getDifficulty(), item.getQuestionType(), testSection.getTest().getFree(), new PageRequest(random.nextInt((int) (countOfQuestions/10)), 10));
                }
            } else {
                Long countOfQuestions = questionRepository.countOfQuestions(item.getQuestionTemplate(), testSection.getTemplate().getDifficulty(), testSection.getTest().getFree());
                Long countOfUserQuestions = questionRepository.countOfQuestions(testSection.getTest().getUsername(), item.getQuestionTemplate(), testSection.getTemplate().getDifficulty(), testSection.getTest().getFree());
                PageRequest pageRequest = getPageRequest(countOfQuestions, countOfUserQuestions);
                candidateQuestionsIds = testRepository.findQuestionIdForTest(testSection.getTest().getUsername(), testSection.getTest().getDifficulty(), item.getQuestionTemplate(), testSection.getTest().getFree(), pageRequest);
                //Check if we can't find unique gre, then we have to find some questions anyway;
                if (candidateQuestionsIds == null || candidateQuestionsIds.isEmpty()){
                    logger.warn("Couldn't find unique gre for user : " + testSection.getTest().getUsername() + ", difficulty: " + testSection.getTemplate().getDifficulty() + ", question_template: " + item.getQuestionTemplate().getLabel() + ", free: " + testSection.getTest().getFree() + " (page_number: " + pageRequest.getPageNumber() + ", page_size: " + pageRequest.getPageSize());
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
                    answeredQuestion.setUsername(testSection.getTest().getUsername());
                    answeredQuestionRepository.save(answeredQuestion);
                    testSection.getAnsweredQuestions().add(answeredQuestion);
                });
            } else {
                AnsweredQuestion answeredQuestion = new AnsweredQuestion(testSection, question);
                answeredQuestion.setNumber(item.getNumber());
                answeredQuestion.setUsername(testSection.getTest().getUsername());
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
