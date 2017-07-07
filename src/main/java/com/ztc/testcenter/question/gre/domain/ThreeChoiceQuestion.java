package com.ztc.testcenter.question.gre.domain;

import javax.persistence.MappedSuperclass;

/**
 * Created by Yubar on 1/18/2017.
 */

@MappedSuperclass
abstract class ThreeChoiceQuestion extends ChoiceQuestion {

    private String choice1;
    private String choice2;
    private String choice3;

    String getChoice1() {
        return choice1;
    }

    void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    String getChoice2() {
        return choice2;
    }

    void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    String getChoice3() {
        return choice3;
    }

    void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    void populateChoices(){
        super.populateChoices();
        getChoices().add(new Choice(choice1));
        getChoices().add(new Choice(choice2));
        getChoices().add(new Choice(choice3));
    }

    void extractChoices() {
        super.extractChoices();
        setChoice1(getChoices().get(0).getText());
        setChoice2(getChoices().get(1).getText());
        setChoice3(getChoices().get(2).getText());
    }
}
