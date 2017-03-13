package com.ztc.testcenter.domain.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Created by Yubar on 1/27/2017.
 */

@Entity
@DiscriminatorValue("DI_NUMERIC")
public class DataInterpretationNumericQuestion extends AbstractNumericQuestion {

    private Integer number;
    private DataInterpretationSetQuestion parent;

    public DataInterpretationNumericQuestion() {
    }

    public DataInterpretationNumericQuestion(DataInterpretationSetQuestion parent) {
        this.parent = parent;
    }

    public DataInterpretationNumericQuestion(DataInterpretationSetQuestion parent, Integer number) {
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
        return QuestionType.GRE_DATA_INTERPRETATION_SET_NUMERIC;
    }
}
