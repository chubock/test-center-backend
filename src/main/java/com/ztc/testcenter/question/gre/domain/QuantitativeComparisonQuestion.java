package com.ztc.testcenter.question.gre.domain;

import javax.persistence.*;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
@DiscriminatorValue("Q_COMPARISON")
public class QuantitativeComparisonQuestion extends Question {

    private String quantityA;
    private String quantityB;

    public String getQuantityA() {
        return quantityA;
    }

    public void setQuantityA(String quantityA) {
        this.quantityA = quantityA;
    }

    public String getQuantityB() {
        return quantityB;
    }

    public void setQuantityB(String quantityB) {
        this.quantityB = quantityB;
    }

    @Transient
    public Integer getAnswer() {
        return Integer.valueOf(getAnswers());
    }

    public void setAnswer(Integer answer) {
        setAnswers(String.valueOf(answer));
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.GRE_QUANTITATIVE_COMPARISON;
    }
}
