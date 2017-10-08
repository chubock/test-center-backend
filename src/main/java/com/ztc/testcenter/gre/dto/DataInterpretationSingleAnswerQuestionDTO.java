package com.ztc.testcenter.gre.dto;

import com.ztc.testcenter.gre.domain.question.DataInterpretationSetQuestion;
import com.ztc.testcenter.gre.domain.question.DataInterpretationSingleAnswerQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class DataInterpretationSingleAnswerQuestionDTO extends AbstractQuantitativeSingleAnswerQuestionDTO<DataInterpretationSingleAnswerQuestion> {

    private Integer number;
    private DataInterpretationSetQuestionDTO parent;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public DataInterpretationSetQuestionDTO getParent() {
        return parent;
    }

    public void setParent(DataInterpretationSetQuestionDTO parent) {
        this.parent = parent;
    }

    @Override
    public DataInterpretationSingleAnswerQuestion convert(DataInterpretationSingleAnswerQuestion question) {
        super.convert(question);
        question.setNumber(getNumber());
        if (getParent() != null)
            question.setParent(getParent().convert(new DataInterpretationSetQuestion()));
        return question;
    }

    public void copy(DataInterpretationSingleAnswerQuestion question) {
        super.copy(question);
        setNumber(question.getNumber());
    }

    public static DataInterpretationSingleAnswerQuestionDTO valueOf(DataInterpretationSingleAnswerQuestion question) {
        if (question == null)
            return null;
        DataInterpretationSingleAnswerQuestionDTO questionDTO = new DataInterpretationSingleAnswerQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
