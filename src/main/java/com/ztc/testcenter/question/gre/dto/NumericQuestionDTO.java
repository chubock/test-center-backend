package com.ztc.testcenter.question.gre.dto;

import com.ztc.testcenter.question.gre.domain.NumericQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class NumericQuestionDTO extends AbstractNumericQuestionDTO<NumericQuestion> {

    public static NumericQuestionDTO valueOf(NumericQuestion question) {
        if (question == null)
            return null;
        NumericQuestionDTO questionDTO = new NumericQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
