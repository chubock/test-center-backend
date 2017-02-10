package com.ztc.testcenter.domain.question;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

/**
 * Created by Yubar on 1/20/2017.
 */

@MappedSuperclass
public abstract class AbstractQuantitativeMultipleAnswerQuestion extends ThreeChoiceQuestion {

    private int maxAnswerCount = 0;

    public int getMaxAnswerCount() {
        return maxAnswerCount;
    }

    public void setMaxAnswerCount(int maxAnswerCount) {
        this.maxAnswerCount = maxAnswerCount;
    }
}
