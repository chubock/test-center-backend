package com.ztc.testcenter.question.gre.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/18/2017.
 */

@MappedSuperclass
public abstract class ChoiceQuestion extends Question {

    private List<Choice> choices = new ArrayList<>();

    @Transient
    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices){
        this.choices = choices;
    }

    void markAnswers(){
        for (Character number: getAnswers().toCharArray()) {
            getChoices().get((int) number - 48).setAnswer(true);
        }
    }

    void populateChoices(){
        getChoices().clear();
    }

    void extractChoices() {
        StringBuilder answersBuilder = new StringBuilder();
        for (int i=0; i< getChoices().size(); i++) {
            if (getChoices().get(i).isAnswer())
                answersBuilder.append(i);
        }
        this.setAnswers(answersBuilder.toString());
    }

    @Override
    public void prepare() {
        this.extractChoices();
    }

    @PostLoad
    @PostUpdate
    @PostPersist
    void afterLoad() {
        populateChoices();
        markAnswers();
    }
}
