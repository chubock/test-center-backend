package com.ztc.testcenter.domain.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Yubar on 1/27/2017.
 */

@Entity
@DiscriminatorValue("DI_MULTIPLE_ANSWER")
public class DataInterpretationMultipleAnswerQuestion extends AbstractQuantitativeMultipleAnswerQuestion {

    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    QuestionType getQuestionType() {
        return QuestionType.GRE_DATA_INTERPRETATION_SET_MULTIPLE_ANSWER;
    }
}
