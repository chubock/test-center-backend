package com.ztc.testcenter.repository.question;

import com.ztc.testcenter.domain.question.QuantitativeMultipleAnswerQuestion;
import com.ztc.testcenter.domain.question.QuantitativeSingleAnswerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yubar on 1/19/2017.
 */
public interface QuantitativeSingleAnswerQuestionRepository extends JpaRepository<QuantitativeSingleAnswerQuestion, Long> {
}
