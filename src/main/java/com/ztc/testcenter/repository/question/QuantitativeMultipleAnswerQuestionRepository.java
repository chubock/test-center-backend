package com.ztc.testcenter.repository.question;

import com.ztc.testcenter.domain.question.QuantitativeComparisonQuestion;
import com.ztc.testcenter.domain.question.QuantitativeMultipleAnswerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yubar on 1/19/2017.
 */
public interface QuantitativeMultipleAnswerQuestionRepository extends JpaRepository<QuantitativeMultipleAnswerQuestion, Long> {
}