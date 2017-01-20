package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.ReadingComprehensionMultipleAnswerQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class ReadingComprehensionMultipleAnswerQuestionDTO extends ChoicesQuestionDTO {
    @Override
    public ReadingComprehensionMultipleAnswerQuestion convert() {
        ReadingComprehensionMultipleAnswerQuestion question = new ReadingComprehensionMultipleAnswerQuestion();
        convert(question);
        return question;
    }

    public static ReadingComprehensionMultipleAnswerQuestionDTO valueOf(ReadingComprehensionMultipleAnswerQuestion question) {
        if (question == null)
            return null;
        ReadingComprehensionMultipleAnswerQuestionDTO questionDTO = new ReadingComprehensionMultipleAnswerQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
