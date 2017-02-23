package com.ztc.testcenter.domain.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Yubar on 1/27/2017.
 */

@Entity
@DiscriminatorValue("DI_SINGLE_ANSWER")
public class DataInterpretationSingleAnswerQuestion extends AbstractQuantitativeSingleAnswerQuestion {

    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    QuestionType getQuestionType() {
        return QuestionType.GRE_DATA_INTERPRETATION_SET_SINGLE_ANSWER;
    }
}
