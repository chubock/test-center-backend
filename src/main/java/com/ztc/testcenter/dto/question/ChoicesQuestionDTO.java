package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.Choice;
import com.ztc.testcenter.domain.question.ChoiceQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/19/2017.
 */
abstract class ChoicesQuestionDTO extends QuestionDTO {
    private List<Choice> choices = new ArrayList<>();

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    void convert(ChoiceQuestion question) {
        super.convert(question);
        getChoices().forEach(choice -> question.getChoices().add(choice.clone()));
    }

    void copy(ChoiceQuestion question) {
        super.copy(question);
        getChoices().clear();
        question.getChoices().forEach(choice -> getChoices().add(choice.clone()));
    }
}
