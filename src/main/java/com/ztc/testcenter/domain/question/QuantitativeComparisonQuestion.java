package com.ztc.testcenter.domain.question;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
@DiscriminatorValue("Q_COMPARISON")
public class QuantitativeComparisonQuestion extends Question {

    private String quantityB;

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
    QuestionType getQuestionType() {
        return QuestionType.GRE_QUANTITATIVE_COMPARISON;
    }
}
