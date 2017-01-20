package com.ztc.testcenter.domain.question;

import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.PostUpdate;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
public class QuantitativeComparisonQuestion extends Question {

    private int answer;
    private List<Choice> choices = new ArrayList<>();

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Transient
    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    void markAnswers(){
        getChoices().get(answer).setAnswer(true);
    }

    void populateChoices(){
    }

    void extractAnswer() {
        for (int i=0; i< getChoices().size(); i++) {
            if (getChoices().get(i).isAnswer()){
                this.answer = i;
            }
        }
    }

    @Override
    public void prepare() {
        this.extractAnswer();
    }

    @PostLoad
    @PostUpdate
    void afterLoad() {
        getChoices().clear();
        getChoices().add(new Choice("Quantity A is greater."));
        getChoices().add(new Choice("Quantity B is greater."));
        getChoices().add(new Choice("The two quantities are equal."));
        getChoices().add(new Choice("The relationship cannot be determined from the information given."));
    }
}
