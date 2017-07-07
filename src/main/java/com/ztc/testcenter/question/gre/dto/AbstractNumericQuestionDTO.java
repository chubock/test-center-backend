package com.ztc.testcenter.question.gre.dto;

import com.ztc.testcenter.question.gre.domain.AbstractNumericQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public abstract class AbstractNumericQuestionDTO<T extends AbstractNumericQuestion> extends QuestionDTO<T> {

    private Double nominatorAnswer;
    private Boolean fraction = false;
    private Double denominatorAnswer;
    private String prefix;
    private String postfix;

    private Double nominator;
    private Double denominator;

    public Double getNominatorAnswer() {
        return nominatorAnswer;
    }

    public void setNominatorAnswer(Double nominatorAnswer) {
        this.nominatorAnswer = nominatorAnswer;
    }

    public Boolean isFraction() {
        return fraction;
    }

    public void setFraction(Boolean fraction) {
        this.fraction = fraction;
    }

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

    public Double getNominator() {
        return nominator;
    }

    public void setNominator(Double nominator) {
        this.nominator = nominator;
    }

    public Double getDenominator() {
        return denominator;
    }

    public void setDenominator(Double denominator) {
        this.denominator = denominator;
    }

    public T convert(T question) {
        super.convert(question);
        question.setNominatorAnswer(getNominatorAnswer());
        question.setFraction(isFraction());
        question.setDenominatorAnswer(getDenominatorAnswer());
        question.setPrefix(getPrefix());
        question.setPostfix(getPostfix());
        return question;
    }

    void copy(T question) {
        super.copy(question);
        setNominatorAnswer(question.getNominatorAnswer());
        setFraction(question.isFraction());
        setDenominatorAnswer(question.getDenominatorAnswer());
        setPrefix(question.getPrefix());
        setPostfix(question.getPostfix());
    }

    @Override
    public void setUserAnswer(String answer) {
        if (answer == null)
            return ;
        String[] answers = answer.split("-");
        if (answers.length == 1) {
            this.nominator = Double.valueOf(answers[0]);
        } else {
            this.nominator = Double.valueOf(answers[0]);
            this.denominator = Double.valueOf(answers[1]);
        }
    }
}
