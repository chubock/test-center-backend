package com.ztc.testcenter.service;

import com.ztc.testcenter.domain.question.QuestionType;
import com.ztc.testcenter.domain.test.AnsweredQuestion;
import com.ztc.testcenter.exceptions.AnsweredQuestionNotFound;
import com.ztc.testcenter.exceptions.AnsweredWritingQuestionNotFound;
import com.ztc.testcenter.repository.test.AnsweredQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by yubar on 6/2/17.
 */

@Service
@Transactional
public class TeacherService {

    private final AnsweredQuestionRepository answeredQuestionRepository;

    @Autowired
    public TeacherService(AnsweredQuestionRepository answeredQuestionRepository) {
        this.answeredQuestionRepository = answeredQuestionRepository;
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
