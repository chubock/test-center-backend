package com.ztc.testcenter.domain.question;

import javax.persistence.*;

/**
 * Created by Yubar on 1/18/2017.
 */

@MappedSuperclass
public abstract class AbstractNumericQuestion extends Question {

    private Double nominatorAnswer;
    private Boolean fraction = false;
    private Double denominatorAnswer;

    @Transient
    public Double getNominatorAnswer() {
        return nominatorAnswer;
    }

    public void setNominatorAnswer(Double nominatorAnswer) {
        this.nominatorAnswer = nominatorAnswer;
    }

    @Transient
    public Boolean isFraction() {
        return fraction;
    }

    public void setFraction(Boolean fraction) {
        this.fraction = fraction;
    }

    @Transient
    public Double getDenominatorAnswer() {
        return denominatorAnswer;
    }

    public void setDenominatorAnswer(Double denominatorAnswer) {
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
            this.nominatorAnswer = Double.valueOf(answers[0]);
        } else {
            this.fraction = true;
            this.nominatorAnswer = Double.valueOf(answers[0]);
            this.denominatorAnswer = Double.valueOf(answers[1]);
        }
    }
}
