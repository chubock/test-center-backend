package com.ztc.testcenter.domain.question;

import javax.persistence.*;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
public class SelectInPassageQuestion extends Question {

    private Integer number;
    private ReadingComprehensionQuestion parent;

    public SelectInPassageQuestion() {
    }

    public SelectInPassageQuestion(ReadingComprehensionQuestion parent) {
        this.parent = parent;
    }

    public SelectInPassageQuestion(ReadingComprehensionQuestion parent, Integer number) {
        this.number = number;
        this.parent = parent;
    }

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    public ReadingComprehensionQuestion getParent() {
        return parent;
    }

    public void setParent(ReadingComprehensionQuestion parent) {
        this.parent = parent;
    }

    public void setAnswer(Integer answer) {
        setAnswers(String.valueOf(answer));
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.GRE_READING_COMPREHENSION_SELECT_IN_PASSAGE;
    }
}
