package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.AbstractNumericQuestion;
import com.ztc.testcenter.domain.question.NumericQuestion;
import com.ztc.testcenter.domain.test.AnsweredQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public abstract class AbstractNumericQuestionDTO<T extends AbstractNumericQuestion> extends QuestionDTO<T> {

    private Double nominatorAnswer;
    private Boolean fraction = false;
    private Double denominatorAnswer;

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
        return question;
    }

    void copy(T question) {
        super.copy(question);
        setNominatorAnswer(question.getNominatorAnswer());
        setFraction(question.isFraction());
        setDenominatorAnswer(question.getDenominatorAnswer());
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
