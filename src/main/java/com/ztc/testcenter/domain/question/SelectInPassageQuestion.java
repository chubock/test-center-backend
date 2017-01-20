package com.ztc.testcenter.domain.question;

import javax.persistence.Entity;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
public class SelectInPassageQuestion extends Question {

    private int answer;

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
