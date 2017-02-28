package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.AbstractNumericQuestion;
import com.ztc.testcenter.domain.question.NumericQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public abstract class AbstractNumericQuestionDTO extends QuestionDTO {

    private Long nominatorAnswer;
    private Boolean fraction = false;
    private Long denominatorAnswer;

    public Long getNominatorAnswer() {
        return nominatorAnswer;
    }

    public void setNominatorAnswer(Long nominatorAnswer) {
        this.nominatorAnswer = nominatorAnswer;
    }

    public Boolean isFraction() {
        return fraction;
    }

    public void setFraction(Boolean fraction) {
        this.fraction = fraction;
    }

    public Long getDenominatorAnswer() {
        return denominatorAnswer;
    }

    public void setDenominatorAnswer(Long denominatorAnswer) {
        this.denominatorAnswer = denominatorAnswer;
    }

    void convert(AbstractNumericQuestion question) {
        super.convert(question);
        question.setNominatorAnswer(getNominatorAnswer());
        question.setFraction(isFraction());
        question.setDenominatorAnswer(getDenominatorAnswer());
    }

    void copy(AbstractNumericQuestion question) {
        super.copy(question);
        setNominatorAnswer(question.getNominatorAnswer());
        setFraction(question.isFraction());
        setDenominatorAnswer(question.getDenominatorAnswer());
    }
}
