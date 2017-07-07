package com.ztc.testcenter.question.gre.domain;

import javax.persistence.*;

/**
 * Created by Yubar on 1/18/2017.
 */

@MappedSuperclass
public abstract class AbstractNumericQuestion extends Question {

    private Double nominatorAnswer;
    private Boolean fraction = false;
    private Double denominatorAnswer;
    private String prefix;
    private String postfix;

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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPostfix() {
        return postfix;
    }

    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }

    @Override
    public void prepare() {
        if (fraction)
            setAnswers(this.nominatorAnswer + "-" + this.denominatorAnswer);
        else
            setAnswers(String.valueOf(this.nominatorAnswer));
    }

    @Override
    public boolean isCorrect(String answers) {
        if (answers == null)
            return false;
        String[] split = answers.split("-");
        if (split.length == 1 && isFraction() || split.length == 2 && !isFraction())
            return false;
        if (isFraction())
            return Double.compare(Double.valueOf(split[0]) * denominatorAnswer, Double.valueOf(split[1]) * nominatorAnswer) == 0;
        else
            return Double.compare(Double.valueOf(split[0]), nominatorAnswer) == 0;
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
