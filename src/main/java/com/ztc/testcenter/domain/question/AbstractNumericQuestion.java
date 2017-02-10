package com.ztc.testcenter.domain.question;

import javax.persistence.*;

/**
 * Created by Yubar on 1/18/2017.
 */

@MappedSuperclass
public abstract class AbstractNumericQuestion extends Question {

    private long nominatorAnswer;
    private boolean fraction;
    private long denominatorAnswer;

    @Transient
    public long getNominatorAnswer() {
        return nominatorAnswer;
    }

    public void setNominatorAnswer(long nominatorAnswer) {
        this.nominatorAnswer = nominatorAnswer;
    }

    @Transient
    public boolean isFraction() {
        return fraction;
    }

    public void setFraction(boolean fraction) {
        this.fraction = fraction;
    }

    @Transient
    public long getDenominatorAnswer() {
        return denominatorAnswer;
    }

    public void setDenominatorAnswer(long denominatorAnswer) {
        this.denominatorAnswer = denominatorAnswer;
    }

    @Override
    public void prepare() {
        if (fraction)
            setAnswers(this.nominatorAnswer + "-" + this.denominatorAnswer);
        else
            setAnswers(String.valueOf(this.nominatorAnswer));
    }

    @PostLoad
    @PostUpdate
    @PostPersist
    void afterLoad() {
        String[] answers = this.getAnswers().split("-");
        if (answers.length == 1) {
            this.fraction = false;
            this.nominatorAnswer = Long.valueOf(answers[0]);
        } else {
            this.fraction = true;
            this.nominatorAnswer = Long.valueOf(answers[0]);
            this.denominatorAnswer = Long.valueOf(answers[1]);
        }
    }
}
