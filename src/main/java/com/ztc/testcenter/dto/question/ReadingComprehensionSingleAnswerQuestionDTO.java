package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.ReadingComprehensionMultipleAnswerQuestion;
import com.ztc.testcenter.domain.question.ReadingComprehensionSingleAnswerQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class ReadingComprehensionSingleAnswerQuestionDTO extends ChoicesQuestionDTO {

    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void convert(ReadingComprehensionSingleAnswerQuestion question) {
        super.convert(question);
        question.setNumber(getNumber());
    }

    public void copy(ReadingComprehensionSingleAnswerQuestion question) {
        super.copy(question);
        setNumber(question.getNumber());
    }

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
