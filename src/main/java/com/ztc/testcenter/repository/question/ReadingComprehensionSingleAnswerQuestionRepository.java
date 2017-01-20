package com.ztc.testcenter.repository.question;

import com.ztc.testcenter.domain.question.QuantitativeMultipleAnswerQuestion;
import com.ztc.testcenter.domain.question.ReadingComprehensionSingleAnswerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yubar on 1/19/2017.
 */
public interface ReadingComprehensionSingleAnswerQuestionRepository extends JpaRepository<ReadingComprehensionSingleAnswerQuestion, Long> {
}
