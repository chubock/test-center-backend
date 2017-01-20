package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.ReadingComprehensionSingleAnswerQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class ReadingComprehensionSingleAnswerQuestionDTO extends ChoicesQuestionDTO {
    @Override
    public ReadingComprehensionSingleAnswerQuestion convert() {
        ReadingComprehensionSingleAnswerQuestion question = new ReadingComprehensionSingleAnswerQuestion();
        convert(question);
        return question;
    }

    public static ReadingComprehensionSingleAnswerQuestionDTO valueOf(ReadingComprehensionSingleAnswerQuestion question) {
        if (question == null)
            return null;
        ReadingComprehensionSingleAnswerQuestionDTO questionDTO = new ReadingComprehensionSingleAnswerQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
