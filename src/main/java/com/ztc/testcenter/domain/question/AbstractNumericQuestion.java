package com.ztc.testcenter.domain.question;

import javax.persistence.*;

/**
 * Created by Yubar on 1/18/2017.
 */

@MappedSuperclass
public abstract class AbstractNumericQuestion extends Question {

    private Long nominatorAnswer;
    private Boolean fraction;
    private Long denominatorAnswer;

    @Transient
    public Long getNominatorAnswer() {
        return nominatorAnswer;
    }

    public void setNominatorAnswer(Long nominatorAnswer) {
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
    public Long getDenominatorAnswer() {
        return denominatorAnswer;
    }

    public void setDenominatorAnswer(Long denominatorAnswer) {
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
