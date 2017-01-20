package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.QuantitativeSingleAnswerQuestion;

/**
 * Created by Yubar on 1/19/2017.
 */

public class QuantitativeSingleAnswerQuestionDTO extends ChoicesQuestionDTO {

    @Override
    public QuantitativeSingleAnswerQuestion convert() {
        QuantitativeSingleAnswerQuestion question = new QuantitativeSingleAnswerQuestion();
        convert(question);
        return question;
    }

    public static QuantitativeSingleAnswerQuestionDTO valueOf(QuantitativeSingleAnswerQuestion question) {
        if (question == null)
            return null;
        QuantitativeSingleAnswerQuestionDTO questionDTO = new QuantitativeSingleAnswerQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}