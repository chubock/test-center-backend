package com.ztc.testcenter.domain.question;

import javax.persistence.MappedSuperclass;

/**
 * Created by Yubar on 1/18/2017.
 */

@MappedSuperclass
abstract class FiveChoiceQuestion extends FourChoiceQuestion {

    private String choice5;

    String getChoice5() {
        return choice5;
    }

    void setChoice5(String choice5) {
        this.choice5 = choice5;
    }

    void populateChoices(){
        super.populateChoices();
        getChoices().add(new Choice(choice5));
    }

    void extractChoices() {
        super.extractChoices();
        setChoice5(getChoices().get(4).getText());
    }
}
