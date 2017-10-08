package com.ztc.testcenter.gre.service;

import com.ztc.testcenter.gre.domain.question.Difficulty;
import com.ztc.testcenter.gre.domain.question.QuestionType;
import com.ztc.testcenter.gre.domain.result.AnsweredQuestionResult;
import com.ztc.testcenter.gre.domain.result.AnsweredQuestionResultItem;
import com.ztc.testcenter.gre.domain.test.AnsweredQuestion;
import com.ztc.testcenter.gre.domain.test.SectionType;
import com.ztc.testcenter.gre.repository.test.AnsweredQuestionRepository;
import com.ztc.testcenter.gre.specification.TestResultSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yubar on 7/7/17.
 */

@Service
@Transactional
public class QuestionResultService {

    private final AnsweredQuestionRepository answeredQuestionRepository;
    private final Pageable pageable = new PageRequest(0, 1);

    @Autowired
    public QuestionResultService(AnsweredQuestionRepository answeredQuestionRepository) {
        this.answeredQuestionRepository = answeredQuestionRepository;
    }

    public List<AnsweredQuestionResult> getResults(TestResultSpecification specification) {
        return Arrays.stream(AnsweredQuestionResult.Type.values())
                .filter(type -> !specification.getTypes().contains(type))
                .map(type -> getResults(specification, type))
                .collect(Collectors.toList());
    }

    private AnsweredQuestionResult getResults(TestResultSpecification specification, AnsweredQuestionResult.Type type) {
        AnsweredQuestionResult answeredQuestionResult = new AnsweredQuestionResult(type);
        for (Enum item : getEnums(type)) {
            TestResultSpecification s = new TestResultSpecification(specification, type, item.name());
            Page<AnsweredQuestion> questions = answeredQuestionRepository.findAll(s, pageable);
            answeredQuestionResult.getItems().add(new AnsweredQuestionResultItem(item.name(), questions.getTotalElements()));
        }
        return answeredQuestionResult;
    }

    private static Enum[] getEnums(AnsweredQuestionResult.Type type) {
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
}
