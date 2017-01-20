package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.Choice;
import com.ztc.testcenter.domain.question.QuantitativeComparisonQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/20/2017.
 */
public class QuantitativeComparisonQuestionDTO extends QuestionDTO {

    private int answer;
    private List<Choice> choices = new ArrayList<>();

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    void convert(QuantitativeComparisonQuestion question) {
        super.convert(question);
        question.setAnswer(getAnswer());
        getChoices().forEach(choice -> question.getChoices().add(choice.clone()));
    }

    @Override
    public QuantitativeComparisonQuestion convert() {
        QuantitativeComparisonQuestion question = new QuantitativeComparisonQuestion();
        convert(question);
        return question;
    }

    void copy(QuantitativeComparisonQuestion question) {
        super.copy(question);
        getChoices().clear();
        question.getChoices().forEach(choice -> getChoices().add(choice.clone()));
    }

    public static QuantitativeComparisonQuestionDTO valueOf(QuantitativeComparisonQuestion question) {
        if (question == null)
            return null;
        QuantitativeComparisonQuestionDTO questionDTO = new QuantitativeComparisonQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
