package com.ztc.testcenter.service;

import com.ztc.testcenter.domain.User;
import com.ztc.testcenter.domain.question.Difficulty;
import com.ztc.testcenter.domain.test.*;
import com.ztc.testcenter.repository.UserRepository;
import com.ztc.testcenter.repository.question.QuestionRepository;
import com.ztc.testcenter.repository.test.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by yubar on 2/28/17.
 */

@Service
@Transactional
public class GreTestService {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final TestRepository testRepository;
    private final TestTemplateRepository testTemplateRepository;
    private final SectionTemplateRepository sectionTemplateRepository;
    private final TestSectionRepository testSectionRepository;
    private final AnsweredQuestionRepository answeredQuestionRepository;
    private final Random random = new Random();

    @Autowired
    public GreTestService(UserRepository userRepository, QuestionRepository questionRepository, TestRepository testRepository, TestTemplateRepository testTemplateRepository, SectionTemplateRepository sectionTemplateRepository, TestSectionRepository testSectionRepository, AnsweredQuestionRepository answeredQuestionRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
        this.testRepository = testRepository;
        this.testTemplateRepository = testTemplateRepository;
        this.sectionTemplateRepository = sectionTemplateRepository;
        this.testSectionRepository = testSectionRepository;
        this.answeredQuestionRepository = answeredQuestionRepository;
    }

    public Test createTest(User user) {
        Test test = new Test();
        test.setUser(user);
        test.setDate(new Date());
        test.setDifficulty(Difficulty.MEDIUM);
        List<TestTemplate> testTemplates = testTemplateRepository.findAll();
        test.setTemplate(testTemplates.get(random.nextInt(testTemplates.size())));
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
        return null;
    }

    private SectionTemplate findSectionTemplate(Test test) {
        if (test.getTestSections().size() == test.getTemplate().getItems().size())
            throw new IllegalArgumentException("All Test Sections for this test has been created");
        TestTemplateItem testTemplateItem = test.getTemplate().getItems().get(test.getTestSections().size());
        List<SectionTemplate> sectionTemplates = sectionTemplateRepository.findBySectionTypeAndDifficulty(testTemplateItem.getSectionType(), findSectionDifficulty(test));
        return sectionTemplates.get(random.nextInt(sectionTemplates.size()));
    }

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
        testSection.getTemplate().getItems().forEach(item -> {
            List<Long> candidateQuestionIds;
            if (item.getQuestionTemplate() == null) {
                candidateQuestionIds = testRepository.findQuestionForTest(testSection.getTest().getUser(), testSection.getTemplate().getDifficulty(), item.getDifficulty(), item.getQuestionType());
            } else {
                candidateQuestionIds = testRepository.findQuestionForTest(testSection.getTest().getUser(), testSection.getTest().getDifficulty(), item.getQuestionTemplate());
            }
            if (candidateQuestionIds == null || candidateQuestionIds.isEmpty())
                throw new IllegalStateException("Can't find any question for this test");
            AnsweredQuestion answeredQuestion = new AnsweredQuestion(questionRepository.getOne(candidateQuestionIds.get(random.nextInt(candidateQuestionIds.size()))));
            answeredQuestion.setNumber(item.getNumber());
            answeredQuestion.setQuestionTemplate(item.getQuestionTemplate());
            answeredQuestion.setTestSection(testSection);
            answeredQuestion.setUser(testSection.getTest().getUser());
            answeredQuestionRepository.save(answeredQuestion);
            testSection.getAnsweredQuestions().add(answeredQuestion);
        });
    }
}
