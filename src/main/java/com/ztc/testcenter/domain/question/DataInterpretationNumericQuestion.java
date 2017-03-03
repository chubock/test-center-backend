package com.ztc.testcenter.domain.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Yubar on 1/27/2017.
 */

@Entity
@DiscriminatorValue("DI_NUMERIC")
public class DataInterpretationNumericQuestion extends AbstractNumericQuestion {

    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.GRE_DATA_INTERPRETATION_SET_NUMERIC;
    }
}
