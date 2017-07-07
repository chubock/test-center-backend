package com.ztc.testcenter.question.gre.dto;

import com.ztc.testcenter.question.gre.domain.QuantitativeMultipleAnswerQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class QuantitativeMultipleAnswerQuestionDTO extends AbstractQuantitativeMultipleAnswerQuestionDTO<QuantitativeMultipleAnswerQuestion> {

    public static QuantitativeMultipleAnswerQuestionDTO valueOf(QuantitativeMultipleAnswerQuestion question) {
        if (question == null)
            return null;
        QuantitativeMultipleAnswerQuestionDTO questionDTO = new QuantitativeMultipleAnswerQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
