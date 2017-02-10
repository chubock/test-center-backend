package com.ztc.testcenter.domain.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
@DiscriminatorValue("SELECT_IN_PASSAGE")
public class SelectInPassageQuestion extends Question {

    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Transient
    public int getAnswer() {
        return Integer.valueOf(getAnswers());
    }

    public void setAnswer(int answer) {
        setAnswers(String.valueOf(answer));
    }

}
