package com.ztc.testcenter.test.gre.service;

import com.ztc.testcenter.question.gre.domain.Difficulty;
import com.ztc.testcenter.question.gre.domain.QuestionType;
import com.ztc.testcenter.test.gre.domain.*;
import com.ztc.testcenter.test.gre.repository.AnsweredQuestionRepository;
import com.ztc.testcenter.test.gre.repository.TestResultItemRepository;
import com.ztc.testcenter.test.gre.repository.TestResultRepository;
import com.ztc.testcenter.test.gre.specification.RecursiveAnsweredQuestionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yubar on 7/7/17.
 */

@Service
@Transactional
public class TestResultService {

    private final TestResultRepository testResultRepository;
    private final TestResultItemRepository testResultItemRepository;
    private final AnsweredQuestionRepository answeredQuestionRepository;
    private final Pageable pageable = new PageRequest(0, 1);

    @Autowired
    public TestResultService(TestResultRepository testResultRepository, TestResultItemRepository testResultItemRepository, AnsweredQuestionRepository answeredQuestionRepository) {
        this.testResultRepository = testResultRepository;
        this.testResultItemRepository = testResultItemRepository;
        this.answeredQuestionRepository = answeredQuestionRepository;
    }

    public void generateTestResults(Test test) {
        for (TestResult.Type type : TestResult.Type.values()) {
            TestResult testResult = new TestResult(type, test);
            testResultRepository.save(testResult);

            for (Enum anEnum : getEnums(type)) {
                RecursiveAnsweredQuestionSpecification specification = new RecursiveAnsweredQuestionSpecification(test, getTitle(type), anEnum.name());
                generateTestResultItems(testResult, specification, anEnum.name());
            }

        }
    }

    private void generateChildTestResults(TestResultItem parentItem, RecursiveAnsweredQuestionSpecification parentSpecification) {
        List<TestResult.Type> types = new ArrayList<>();
        TestResultItem tempParent = parentItem;
        while (tempParent != null) {
            types.add(tempParent.getTestResult().getType());
            tempParent = tempParent.getTestResult().getParentItem();
        }

        for (TestResult.Type type : TestResult.Type.values()) {
            if (! types.contains(type)) {

                TestResult testResult = new TestResult(type, parentItem);
                testResultRepository.save(testResult);

                for (Enum anEnum : getEnums(type)) {
                    RecursiveAnsweredQuestionSpecification specification = new RecursiveAnsweredQuestionSpecification(parentSpecification, getTitle(type), anEnum.name());
                    generateTestResultItems(testResult, specification, anEnum.name());
                }
            }
        }
    }

    private void generateTestResultItems(TestResult testResult, RecursiveAnsweredQuestionSpecification specification, String title) {
        Page<AnsweredQuestion> questions = answeredQuestionRepository.findAll(specification, pageable);
        TestResultItem item = new TestResultItem(testResult, title, (int) questions.getTotalElements());
        testResultItemRepository.save(item);
        generateChildTestResults(item, specification);
    }

    private static Enum[] getEnums(TestResult.Type type) {
        switch (type) {
            case DIFFICULTY:
                return Difficulty.values();
            case CORRECTNESS:
                return AnsweredQuestion.Status.values();
            case SECTION_TYPE:
                return SectionType.Group.values();
            case QUESTION_TYPE:
                return QuestionType.values();
        }
        throw new IllegalArgumentException();
    }

    private static String getTitle(TestResult.Type type) {
        switch (type) {
            case QUESTION_TYPE:
                return "questionType";
            case SECTION_TYPE:
                return "testSectionGroup";
            case CORRECTNESS:
                return "status";
            case DIFFICULTY:
                return "difficulty";
        }
        throw new IllegalArgumentException();
    }

}
