package com.ztc.testcenter.question.gre.domain;

import javax.persistence.MappedSuperclass;

/**
 * Created by Yubar on 1/20/2017.
 */

@MappedSuperclass
public abstract class AbstractQuantitativeMultipleAnswerQuestion extends DynamicChoiceQuestion {

    private Integer maxAnswerCount = 0;

    public Integer getMaxAnswerCount() {
        return maxAnswerCount;
    }

    public void setMaxAnswerCount(Integer maxAnswerCount) {
        this.maxAnswerCount = maxAnswerCount;
    }
}
