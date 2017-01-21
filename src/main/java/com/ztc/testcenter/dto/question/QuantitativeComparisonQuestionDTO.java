package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.Choice;
import com.ztc.testcenter.domain.question.QuantitativeComparisonQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/20/2017.
 */
public class QuantitativeComparisonQuestionDTO extends QuestionDTO {

    private String quantityB;
    private int answer;

    public String getQuantityB() {
        return quantityB;
    }

    public void setQuantityB(String quantityB) {
        this.quantityB = quantityB;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
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

    public static QuantitativeComparisonQuestionDTO valueOf(QuantitativeComparisonQuestion question) {
        if (question == null)
            return null;
        QuantitativeComparisonQuestionDTO questionDTO = new QuantitativeComparisonQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
