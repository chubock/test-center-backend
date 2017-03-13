package com.ztc.testcenter.domain.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Created by Yubar on 1/27/2017.
 */

@Entity
@DiscriminatorValue("DI_SINGLE_ANSWER")
public class DataInterpretationSingleAnswerQuestion extends AbstractQuantitativeSingleAnswerQuestion {

    private Integer number;
    private DataInterpretationSetQuestion parent;

    public DataInterpretationSingleAnswerQuestion() {
    }

    public DataInterpretationSingleAnswerQuestion(DataInterpretationSetQuestion parent) {
        this.parent = parent;
    }

    public DataInterpretationSingleAnswerQuestion(DataInterpretationSetQuestion parent, Integer number) {
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
    public DataInterpretationSetQuestion getParent() {
        return parent;
    }

    public void setParent(DataInterpretationSetQuestion parent) {
        this.parent = parent;
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.GRE_DATA_INTERPRETATION_SET_SINGLE_ANSWER;
    }
}
