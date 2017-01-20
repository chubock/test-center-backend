package com.ztc.testcenter.domain.question;

import javax.persistence.ElementCollection;
import javax.persistence.MappedSuperclass;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/18/2017.
 */

@MappedSuperclass
abstract class DynamicChoiceQuestion extends SixChoiceQuestion {

    private int choicesCount;
    private List<String> otherChoices = new ArrayList<>();

    @ElementCollection
    List<String> getOtherChoices() {
        return otherChoices;
    }

    void setOtherChoices(List<String> otherChoices) {
        this.otherChoices = otherChoices;
    }

    void populateChoices(){
        if (choicesCount > 5) {
            super.populateChoices();
            getOtherChoices().forEach(choice -> getChoices().add(new Choice(choice)));
        } else {
            getChoices().clear();
            switch (choicesCount) {
                case 5:
                    getChoices().add(new Choice(getChoice5()));
                case 4:
                    getChoices().add(new Choice(getChoice4()));
                case 3:
                    getChoices().add(new Choice(getChoice3()));
                case 2:
                    getChoices().add(new Choice(getChoice2()));
                case 1:
                    getChoices().add(new Choice(getChoice1()));
            }
        }
        for (Character number: getAnswers().toCharArray()) {
            getChoices().get(Integer.valueOf(number)).setAnswer(true);
        }
    }

    void extractChoices() {
        if (getChoices().size() > 5) {
            super.extractChoices();
            getOtherChoices().clear();
            getChoices().subList(6, getChoices().size()).forEach(choice -> getOtherChoices().add(choice.getText()));
            getOtherChoices().forEach(choice -> getChoices().add(new Choice(choice)));
        } else {
            getChoices().clear();
            switch (getChoices().size()) {
                case 5:
                    setChoice5(getChoices().get(4).getText());
                case 4:
                    setChoice4(getChoices().get(3).getText());;
                case 3:
                    setChoice3(getChoices().get(2).getText());
                case 2:
                    setChoice2(getChoices().get(1).getText());
                case 1:
                    setChoice1(getChoices().get(0).getText());
            }
        }
    }
}
