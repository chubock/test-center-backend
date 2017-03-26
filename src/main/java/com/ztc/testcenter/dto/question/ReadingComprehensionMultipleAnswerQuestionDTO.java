package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.ReadingComprehensionMultipleAnswerQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class ReadingComprehensionMultipleAnswerQuestionDTO extends ChoicesQuestionDTO {
    private Integer number;
    private ReadingComprehensionQuestionDTO parent;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public ReadingComprehensionQuestionDTO getParent() {
        return parent;
    }

    public void setParent(ReadingComprehensionQuestionDTO parent) {
        this.parent = parent;
    }
    public void convert(ReadingComprehensionMultipleAnswerQuestion question) {
        super.convert(question);
        question.setNumber(getNumber());
        if (getParent() != null)
            question.setParent(getParent().convert());
    }

    public void copy(ReadingComprehensionMultipleAnswerQuestion question) {
        super.copy(question);
        setNumber(question.getNumber());
    }

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
