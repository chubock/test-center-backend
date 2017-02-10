package com.ztc.testcenter.domain.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
@DiscriminatorValue("RC_SINGLE_ANSWER")
public class ReadingComprehensionSingleAnswerQuestion extends FiveChoiceQuestion {

    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

}
