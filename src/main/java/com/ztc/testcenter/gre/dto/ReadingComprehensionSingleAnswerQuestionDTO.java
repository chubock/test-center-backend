package com.ztc.testcenter.gre.dto;

import com.ztc.testcenter.gre.domain.question.ReadingComprehensionQuestion;
import com.ztc.testcenter.gre.domain.question.ReadingComprehensionSingleAnswerQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class ReadingComprehensionSingleAnswerQuestionDTO extends ChoicesQuestionDTO<ReadingComprehensionSingleAnswerQuestion> {

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

    @Override
    public ReadingComprehensionSingleAnswerQuestion convert(ReadingComprehensionSingleAnswerQuestion question) {
        super.convert(question);
        question.setNumber(getNumber());
        if (getParent() != null)
            question.setParent(getParent().convert(new ReadingComprehensionQuestion()));
        return question;
    }

    public void copy(ReadingComprehensionSingleAnswerQuestion question) {
        super.copy(question);
        setNumber(question.getNumber());
    }

    public static ReadingComprehensionSingleAnswerQuestionDTO valueOf(ReadingComprehensionSingleAnswerQuestion question) {
        if (question == null)
            return null;
        ReadingComprehensionSingleAnswerQuestionDTO questionDTO = new ReadingComprehensionSingleAnswerQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
