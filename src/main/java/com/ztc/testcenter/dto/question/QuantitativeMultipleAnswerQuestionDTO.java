package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.QuantitativeMultipleAnswerQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class QuantitativeMultipleAnswerQuestionDTO extends ChoicesQuestionDTO {

    private int maxAnswerCount = 0;

    public int getMaxAnswerCount() {
        return maxAnswerCount;
    }

    public void setMaxAnswerCount(int maxAnswerCount) {
        this.maxAnswerCount = maxAnswerCount;
    }

    public void convert(QuantitativeMultipleAnswerQuestion question) {
        super.convert(question);
        question.setMaxAnswerCount(getMaxAnswerCount());
    }

    @Override
    public QuantitativeMultipleAnswerQuestion convert() {
        QuantitativeMultipleAnswerQuestion question = new QuantitativeMultipleAnswerQuestion();
        convert(question);
        return question;
    }

    public void copy(QuantitativeMultipleAnswerQuestion question) {
        super.copy(question);
        setMaxAnswerCount(question.getMaxAnswerCount());
    }

    public static QuantitativeMultipleAnswerQuestionDTO valueOf(QuantitativeMultipleAnswerQuestion question) {
        if (question == null)
            return null;
        QuantitativeMultipleAnswerQuestionDTO questionDTO = new QuantitativeMultipleAnswerQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
