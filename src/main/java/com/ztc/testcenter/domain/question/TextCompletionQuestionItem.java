package com.ztc.testcenter.domain.question;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
public class TextCompletionQuestionItem {

    private long id;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String choice5;
    private int answer;
    private List<Choice> choices = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    @NotNull
    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    @NotNull
    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public String getChoice5() {
        return choice5;
    }

    public void setChoice5(String choice5) {
        this.choice5 = choice5;
    }

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

    void prepare() {
        setChoice1(getChoices().get(0).getText());
        setChoice2(getChoices().get(1).getText());
        setChoice3(getChoices().get(2).getText());
        if (getChoices().size() > 3) {
            setChoice4(getChoices().get(3).getText());
            setChoice5(getChoices().get(4).getText());
        } else {
            setChoice4(null);
            setChoice5(null);
        }
        for (int i = 0; i < getChoices().size(); i++)
            if (getChoices().get(i).isAnswer())
                setAnswer(i);
    }

    @PostLoad
    @PostUpdate
    public void populateChoices() {
        getChoices().add(new Choice(choice1));
        getChoices().add(new Choice(choice2));
        getChoices().add(new Choice(choice3));
        if (choice4 != null)
            getChoices().add(new Choice(choice4));
        if (choice5 != null)
            getChoices().add(new Choice(choice5));
        getChoices().get(this.answer).setAnswer(true);
    }
}
