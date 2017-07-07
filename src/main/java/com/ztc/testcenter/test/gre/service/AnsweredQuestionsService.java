package com.ztc.testcenter.test.gre.service;

import com.ztc.testcenter.question.gre.domain.Question;
import com.ztc.testcenter.question.gre.domain.QuestionType;
import com.ztc.testcenter.question.gre.service.QuestionService;
import com.ztc.testcenter.test.gre.domain.AnsweredQuestion;
import com.ztc.testcenter.test.gre.exceptions.AnsweredQuestionNotFound;
import com.ztc.testcenter.test.gre.exceptions.AnsweredWritingQuestionNotFound;
import com.ztc.testcenter.test.gre.repository.AnsweredQuestionRepository;
import com.ztc.testcenter.test.gre.specification.AnsweredQuestionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yubar on 7/7/17.
 */

@Service
@Transactional
public class AnsweredQuestionsService {

    private final AnsweredQuestionRepository answeredQuestionRepository;
    private final QuestionService questionService;

    @Autowired
    public AnsweredQuestionsService(AnsweredQuestionRepository answeredQuestionRepository, QuestionService questionService) {
        this.answeredQuestionRepository = answeredQuestionRepository;
        this.questionService = questionService;
    }

    public Page<AnsweredQuestion> findAnsweredQuestions(AnsweredQuestionSpecification specification, Pageable pageable) {
        Page<AnsweredQuestion> page = answeredQuestionRepository.findAll(specification, pageable);
        findQuestions(page.getContent());
        return page;
    }

    private void findQuestions(List<AnsweredQuestion> answeredQuestions) {
        Map<QuestionType, List<Long>> questionTypeIds = new HashMap<>();
        answeredQuestions.forEach(answeredQuestion -> {
            if (!questionTypeIds.containsKey(answeredQuestion.getQuestionType()))
                questionTypeIds.put(answeredQuestion.getQuestionType(), new ArrayList<>());
            questionTypeIds.get(answeredQuestion.getQuestionType()).add(answeredQuestion.getQuestion().getId());
        });
        questionTypeIds.forEach((questionType, ids) -> {
            List<? extends Question> questions = questionService.findQuestions(questionType, ids);
            questions.forEach(question -> {
                for (AnsweredQuestion answeredQuestion : answeredQuestions) {
                    if (answeredQuestion.getQuestion().getId().equals(question.getId())){
                        answeredQuestion.setQuestion(question);
                        break;
                    }
                }
            });
        });
    }

    public void scoreAnsweredQuestion(Long id, Double score, String comment) {
        AnsweredQuestion answeredQuestion = answeredQuestionRepository.findOne(id);
        if (answeredQuestion == null)
            throw new AnsweredQuestionNotFound();
        if (!answeredQuestion.getQuestionType().equals(QuestionType.GRE_WRITING_ISSUE) && !answeredQuestion.getQuestionType().equals(QuestionType.GRE_WRITING_ARGUMENT))
            throw new AnsweredWritingQuestionNotFound();
        answeredQuestion.setScore(score);
        answeredQuestion.setComment(comment);
    }
}
