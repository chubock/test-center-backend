package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.NumericQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class NumericQuestionDTO extends QuestionDTO {

    private long nominator;
    private boolean fraction;
    private long denominator;

    public long getNominator() {
        return nominator;
    }

    public void setNominator(long nominator) {
        this.nominator = nominator;
    }

    public boolean isFraction() {
        return fraction;
    }

    public void setFraction(boolean fraction) {
        this.fraction = fraction;
    }

    public long getDenominator() {
        return denominator;
    }

    public void setDenominator(long denominator) {
        this.denominator = denominator;
    }

    void convert(NumericQuestion question) {
        super.convert(question);
        question.setNominator(getNominator());
        question.setFraction(isFraction());
        question.setDenominator(getDenominator());
    }

    @Override
    public NumericQuestion convert() {
        NumericQuestion question = new NumericQuestion();
        convert(question);
        return question;
    }

    void copy(NumericQuestion question) {
        super.copy(question);
        setNominator(question.getNominator());
        setFraction(question.isFraction());
        setDenominator(question.getDenominator());
    }

    public static NumericQuestionDTO valueOf(NumericQuestion question) {
        if (question == null)
            return null;
        NumericQuestionDTO questionDTO = new NumericQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
