package com.ztc.testcenter.gre.domain.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Yubar on 1/18/2017.
 */

@Entity
@DiscriminatorValue("NUMERIC")
public class NumericQuestion extends AbstractNumericQuestion {

    @Override
    public QuestionType getQuestionType() {
        if (isFraction())
            return QuestionType.GRE_NUMERIC_FRACTION;
        return QuestionType.GRE_NUMERIC;
    }
}
