package com.ztc.testcenter.repository.question;

import com.ztc.testcenter.domain.question.NumericQuestion;
import com.ztc.testcenter.domain.question.QuantitativeComparisonQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yubar on 1/19/2017.
 */
public interface QuantitativeComparisonQuestionRepository extends JpaRepository<QuantitativeComparisonQuestion, Long> {
}
