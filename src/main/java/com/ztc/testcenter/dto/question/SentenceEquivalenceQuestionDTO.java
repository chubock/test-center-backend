package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.SentenceEquivalenceQuestion;

/**
 * Created by Yubar on 1/19/2017.
 */

public class SentenceEquivalenceQuestionDTO extends ChoicesQuestionDTO {

    @Override
    public SentenceEquivalenceQuestion convert() {
        SentenceEquivalenceQuestion question = new SentenceEquivalenceQuestion();
        convert(question);
        return question;
    }

    public static SentenceEquivalenceQuestionDTO valueOf(SentenceEquivalenceQuestion question) {
        if (question == null)
            return null;
        SentenceEquivalenceQuestionDTO questionDTO = new SentenceEquivalenceQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
