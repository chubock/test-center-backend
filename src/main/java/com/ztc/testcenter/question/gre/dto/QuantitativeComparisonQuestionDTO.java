package com.ztc.testcenter.question.gre.dto;

import com.ztc.testcenter.question.gre.domain.QuantitativeComparisonQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class QuantitativeComparisonQuestionDTO extends QuestionDTO<QuantitativeComparisonQuestion> {

    private String quantityA;
    private String quantityB;
    private Integer answer;
    private Integer selected;

    public String getQuantityA() {
        return quantityA;
    }

    public void setQuantityA(String quantityA) {
        this.quantityA = quantityA;
    }

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

    @Override
    public QuantitativeComparisonQuestion convert(QuantitativeComparisonQuestion question) {
        super.convert(question);
        question.setQuantityA(getQuantityA());
        question.setQuantityB(getQuantityB());
        question.setAnswer(getAnswer());
        return question;
    }

    void copy(QuantitativeComparisonQuestion question) {
        super.copy(question);
        setQuantityA(question.getQuantityA());
        setQuantityB(question.getQuantityB());
        setAnswer(question.getAnswer());
    }

    @Override
    public void setUserAnswer(String answer) {
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
