package com.ztc.testcenter.domain.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
public class ReadingComprehensionSingleAnswerQuestion extends FiveChoiceQuestion {

    private Integer number;
    private ReadingComprehensionQuestion parent;

    public ReadingComprehensionSingleAnswerQuestion() {
    }

    public ReadingComprehensionSingleAnswerQuestion(ReadingComprehensionQuestion parent) {
        this.parent = parent;
    }

    public ReadingComprehensionSingleAnswerQuestion(ReadingComprehensionQuestion parent, Integer number) {
        this.number = number;
        this.parent = parent;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    public ReadingComprehensionQuestion getParent() {
        return parent;
    }

    public void setParent(ReadingComprehensionQuestion parent) {
        this.parent = parent;
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.GRE_READING_COMPREHENSION_SINGLE_ANSWER;
    }
}
