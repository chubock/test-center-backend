package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.NumericQuestion;
import com.ztc.testcenter.domain.question.Question;

/**
 * Created by Yubar on 1/20/2017.
 */
public class NumericQuestionDTO extends AbstractNumericQuestionDTO {

    public static NumericQuestionDTO valueOf(NumericQuestion question) {
        if (question == null)
            return null;
        NumericQuestionDTO questionDTO = new NumericQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }

    @Override
    public NumericQuestion convert() {
        NumericQuestion question = new NumericQuestion();
        convert(question);
        return question;
    }
}
