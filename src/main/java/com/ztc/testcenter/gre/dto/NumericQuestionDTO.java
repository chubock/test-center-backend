package com.ztc.testcenter.gre.dto;

import com.ztc.testcenter.gre.domain.question.NumericQuestion;

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
