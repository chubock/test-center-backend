package com.ztc.testcenter.question.gre.dto;

import com.ztc.testcenter.question.gre.domain.QuantitativeSingleAnswerQuestion;

/**
 * Created by Yubar on 1/19/2017.
 */

public class QuantitativeSingleAnswerQuestionDTO extends AbstractQuantitativeSingleAnswerQuestionDTO<QuantitativeSingleAnswerQuestion> {

    public static QuantitativeSingleAnswerQuestionDTO valueOf(QuantitativeSingleAnswerQuestion question) {
        if (question == null)
            return null;
        QuantitativeSingleAnswerQuestionDTO questionDTO = new QuantitativeSingleAnswerQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
