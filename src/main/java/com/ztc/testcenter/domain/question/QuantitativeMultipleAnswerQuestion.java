package com.ztc.testcenter.domain.question;

import javax.persistence.Entity;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
public class QuantitativeMultipleAnswerQuestion extends ThreeChoiceQuestion {

    private int maxAnswerCount = 0;

    public int getMaxAnswerCount() {
        return maxAnswerCount;
    }

    public void setMaxAnswerCount(int maxAnswerCount) {
        this.maxAnswerCount = maxAnswerCount;
    }
}
