package com.ztc.testcenter.gre.dto;

import com.ztc.testcenter.gre.domain.question.Choice;
import com.ztc.testcenter.gre.domain.question.ChoiceQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/19/2017.
 */
abstract class ChoicesQuestionDTO<T extends ChoiceQuestion> extends QuestionDTO<T> {
    private List<Choice> choices = new ArrayList<>();

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    @Override
    public T convert(T question) {
        super.convert(question);
        getChoices().forEach(choice -> question.getChoices().add(choice.clone()));
        return question;
    }

    void copy(T question) {
        super.copy(question);
        getChoices().clear();
        question.getChoices().forEach(choice -> getChoices().add(choice.clone()));
    }

    @Override
    public void setUserAnswer(String answer) {
        if (answer == null)
            return ;
        for (Character character: answer.toCharArray()) {
            if (Character.isDigit(character))
                getChoices().get(Character.digit(character, 10)).setSelected(true);
        }
    }
}
