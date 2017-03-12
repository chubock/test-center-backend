package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.Choice;
import com.ztc.testcenter.domain.question.QuantitativeComparisonQuestion;
import com.ztc.testcenter.domain.test.AnsweredQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/20/2017.
 */
public class QuantitativeComparisonQuestionDTO extends QuestionDTO {

    private String quantityB;
    private Integer answer;
    private Integer selected;

    public String getQuantityB() {
        return quantityB;
    }

    public void setQuantityB(String quantityB) {
        this.quantityB = quantityB;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    void convert(QuantitativeComparisonQuestion question) {
        super.convert(question);
        question.setQuantityB(getQuantityB());
        question.setAnswer(getAnswer());
    }

    @Override
    public QuantitativeComparisonQuestion convert() {
        QuantitativeComparisonQuestion question = new QuantitativeComparisonQuestion();
        convert(question);
        return question;
    }

    void copy(QuantitativeComparisonQuestion question) {
        super.copy(question);
        setQuantityB(question.getQuantityB());
        setAnswer(question.getAnswer());
    }

    @Override
    public void setAnswer(String answer) {
        if (answer == null)
            return ;
        setSelected(Integer.valueOf(answer));
    }

    public static QuantitativeComparisonQuestionDTO valueOf(QuantitativeComparisonQuestion question) {
        if (question == null)
            return null;
        QuantitativeComparisonQuestionDTO questionDTO = new QuantitativeComparisonQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
