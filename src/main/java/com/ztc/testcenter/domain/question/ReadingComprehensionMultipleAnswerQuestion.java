package com.ztc.testcenter.domain.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
@DiscriminatorValue("RC_MULTIPLE_ANSWER")
public class ReadingComprehensionMultipleAnswerQuestion extends ThreeChoiceQuestion {
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
