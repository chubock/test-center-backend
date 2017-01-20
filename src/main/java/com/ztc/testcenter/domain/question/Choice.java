package com.ztc.testcenter.domain.question;

import java.io.Serializable;

/**
 * Created by Yubar on 1/18/2017.
 */

public class Choice implements Serializable {

    private String text;
    private boolean answer;
    private boolean selected;

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

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Choice clone() {
        Choice choice = new Choice(getText());
        choice.setAnswer(isAnswer());
        choice.setSelected(isSelected());
        return choice;
    }
}
