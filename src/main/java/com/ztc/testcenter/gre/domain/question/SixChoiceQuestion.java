package com.ztc.testcenter.gre.domain.question;

import javax.persistence.MappedSuperclass;

/**
 * Created by Yubar on 1/18/2017.
 */

@MappedSuperclass
abstract class SixChoiceQuestion extends FiveChoiceQuestion {

    private String choice6;

    String getChoice6() {
        return choice6;
    }

    void setChoice6(String choice6) {
        this.choice6 = choice6;
    }

    void populateChoices(){
        super.populateChoices();
        getChoices().add(new Choice(choice6));
    }

    void extractChoices() {
        super.extractChoices();
        setChoice6(getChoices().get(5).getText());
    }
}