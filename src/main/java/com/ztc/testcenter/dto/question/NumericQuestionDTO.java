package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.NumericQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class NumericQuestionDTO extends QuestionDTO {

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

    void convert(NumericQuestion question) {
        super.convert(question);
        question.setNominatorAnswer(getNominatorAnswer());
        question.setFraction(isFraction());
        question.setDenominatorAnswer(getDenominatorAnswer());
    }

    @Override
    public NumericQuestion convert() {
        NumericQuestion question = new NumericQuestion();
        convert(question);
        return question;
    }

    void copy(NumericQuestion question) {
        super.copy(question);
        setNominatorAnswer(question.getNominatorAnswer());
        setFraction(question.isFraction());
        setDenominatorAnswer(question.getDenominatorAnswer());
    }

    public static NumericQuestionDTO valueOf(NumericQuestion question) {
        if (question == null)
            return null;
        NumericQuestionDTO questionDTO = new NumericQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
