package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.WritingQuestion;

/**
 * Created by Yubar on 1/19/2017.
 */
public class WritingQuestionDTO extends QuestionDTO {

    @Override
    public WritingQuestion convert() {
        WritingQuestion question = new WritingQuestion();
        convert(question);
        return question;
    }

    public static WritingQuestionDTO valueOf(WritingQuestion question) {
        if (question == null)
            return null;
        WritingQuestionDTO questionDTO = new WritingQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
