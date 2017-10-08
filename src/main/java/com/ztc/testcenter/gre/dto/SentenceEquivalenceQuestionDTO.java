package com.ztc.testcenter.gre.dto;

import com.ztc.testcenter.gre.domain.question.SentenceEquivalenceQuestion;

/**
 * Created by Yubar on 1/19/2017.
 */

public class SentenceEquivalenceQuestionDTO extends ChoicesQuestionDTO<SentenceEquivalenceQuestion> {

    public static SentenceEquivalenceQuestionDTO valueOf(SentenceEquivalenceQuestion question) {
        if (question == null)
            return null;
        SentenceEquivalenceQuestionDTO questionDTO = new SentenceEquivalenceQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
