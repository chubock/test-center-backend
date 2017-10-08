package com.ztc.testcenter.gre.domain.question;

import java.io.Serializable;

/**
 * Created by Yubar on 1/18/2017.
 */

public class Choice implements Serializable {

    private String text;
    private Boolean answer = false;
    private Boolean selected = false;

    public Choice() {
    }

    public Choice(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean isAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    public Boolean isSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Choice clone() {
        Choice choice = new Choice(getText());
        choice.setAnswer(isAnswer());
        choice.setSelected(isSelected());
        return choice;
    }
}
