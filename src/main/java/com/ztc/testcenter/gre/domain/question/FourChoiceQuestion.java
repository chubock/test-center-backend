package com.ztc.testcenter.gre.domain.question;

import javax.persistence.MappedSuperclass;

/**
 * Created by Yubar on 1/18/2017.
 */

@MappedSuperclass
abstract class FourChoiceQuestion extends ThreeChoiceQuestion {

    private String choice4;

    String getChoice4() {
        return choice4;
    }

    void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    void populateChoices(){
        super.populateChoices();
        getChoices().add(new Choice(choice4));
    }

    void extractChoices() {
        super.extractChoices();
        setChoice4(getChoices().get(3).getText());
    }
}
