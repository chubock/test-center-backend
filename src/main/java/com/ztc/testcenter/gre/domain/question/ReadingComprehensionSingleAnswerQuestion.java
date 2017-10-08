package com.ztc.testcenter.gre.domain.question;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
public class ReadingComprehensionSingleAnswerQuestion extends FiveChoiceQuestion implements InnerQuestion<ReadingComprehensionQuestion> {

    private Integer number;
    private ReadingComprehensionQuestion parent;

    protected ReadingComprehensionSingleAnswerQuestion() {
    }

    public ReadingComprehensionSingleAnswerQuestion(ReadingComprehensionQuestion parent, Integer number) {
        this.number = number;
        this.parent = parent;
    }

    @Override
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
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
