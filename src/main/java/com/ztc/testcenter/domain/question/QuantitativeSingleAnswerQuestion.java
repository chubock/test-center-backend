package com.ztc.testcenter.domain.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
public class QuantitativeSingleAnswerQuestion extends AbstractQuantitativeSingleAnswerQuestion {
    @Override
    public QuestionType getQuestionType() {
        return QuestionType.GRE_QUANTITATIVE_SINGLE_ANSWER;
    }
}
