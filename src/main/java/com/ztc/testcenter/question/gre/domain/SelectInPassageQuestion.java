package com.ztc.testcenter.question.gre.domain;

import javax.persistence.*;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
public class SelectInPassageQuestion extends Question implements InnerQuestion<ReadingComprehensionQuestion> {

    private Integer number;
    private ReadingComprehensionQuestion parent;

    protected SelectInPassageQuestion() {
    }

    public SelectInPassageQuestion(ReadingComprehensionQuestion parent, Integer number) {
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

    @Transient
    public Integer getAnswer() {
        return Integer.valueOf(getAnswers());
    }

    @Override
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
