package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.AbstractNumericQuestion;
import com.ztc.testcenter.domain.question.NumericQuestion;
import com.ztc.testcenter.domain.test.AnsweredQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public abstract class AbstractNumericQuestionDTO extends QuestionDTO {

    private Long nominatorAnswer;
    private Boolean fraction = false;
    private Long denominatorAnswer;

    private Long nominator;
    private Long denominator;

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

    public Long getNominator() {
        return nominator;
    }

    public void setNominator(Long nominator) {
        this.nominator = nominator;
    }

    public Long getDenominator() {
        return denominator;
    }

    public void setDenominator(Long denominator) {
        this.denominator = denominator;
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

    @Override
    public void setUserAnswer(String answer) {
        if (answer == null)
            return ;
        String[] answers = answer.split("-");
        if (answers.length == 1) {
            this.nominator = Long.valueOf(answers[0]);
        } else {
            this.nominator = Long.valueOf(answers[0]);
            this.denominator = Long.valueOf(answers[1]);
        }
    }
}
