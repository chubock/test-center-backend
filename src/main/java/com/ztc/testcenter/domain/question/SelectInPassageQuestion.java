package com.ztc.testcenter.domain.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
public class SelectInPassageQuestion extends Question {

    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
        return QuestionType.GRE_READING_COMPREHENSION_SELECT_IN_PASSAGE;
    }
}
