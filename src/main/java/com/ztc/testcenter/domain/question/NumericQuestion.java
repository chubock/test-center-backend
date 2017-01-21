package com.ztc.testcenter.domain.question;

import javax.persistence.Entity;

/**
 * Created by Yubar on 1/18/2017.
 */

@Entity
public class NumericQuestion extends Question {

    private long nominatorAnswer;
    private boolean fraction;
    private long denominatorAnswer;

    public long getNominatorAnswer() {
        return nominatorAnswer;
    }

    public void setNominatorAnswer(long nominatorAnswer) {
        this.nominatorAnswer = nominatorAnswer;
    }

    public boolean isFraction() {
        return fraction;
    }

    public void setFraction(boolean fraction) {
        this.fraction = fraction;
    }

    public long getDenominatorAnswer() {
        return denominatorAnswer;
    }

    public void setDenominatorAnswer(long denominatorAnswer) {
        this.denominatorAnswer = denominatorAnswer;
    }
}
