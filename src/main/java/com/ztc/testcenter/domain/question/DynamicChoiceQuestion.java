package com.ztc.testcenter.domain.question;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/18/2017.
 */

@MappedSuperclass
abstract class DynamicChoiceQuestion extends SixChoiceQuestion {

    private Integer choicesCount = 0;
    private List<String> otherChoices = new ArrayList<>();

    @NotNull
    @Column(nullable = false)
    public Integer getChoicesCount() {
        return choicesCount;
    }

    public void setChoicesCount(Integer choicesCount) {
        this.choicesCount = choicesCount;
    }

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
    }

    void extractChoices() {
        if (getChoices().size() > 5) {
            super.extractChoices();
            getOtherChoices().clear();
            getChoices().subList(6, getChoices().size()).forEach(choice -> getOtherChoices().add(choice.getText()));
            getOtherChoices().forEach(choice -> getChoices().add(new Choice(choice)));
        } else {
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
            StringBuilder answersBuilder = new StringBuilder();
            for (int i=0; i< getChoices().size(); i++) {
                if (getChoices().get(i).isAnswer())
                    answersBuilder.append(i);
            }
            this.setAnswers(answersBuilder.toString());
        }
    }

    @Override
    public void prepare() {
        super.prepare();
        choicesCount = getChoices().size();
    }
}
