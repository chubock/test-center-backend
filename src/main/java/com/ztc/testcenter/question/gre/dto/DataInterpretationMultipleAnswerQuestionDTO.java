package com.ztc.testcenter.question.gre.dto;

import com.ztc.testcenter.question.gre.domain.DataInterpretationMultipleAnswerQuestion;
import com.ztc.testcenter.question.gre.domain.DataInterpretationSetQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class DataInterpretationMultipleAnswerQuestionDTO extends AbstractQuantitativeMultipleAnswerQuestionDTO<DataInterpretationMultipleAnswerQuestion> {

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
    public DataInterpretationMultipleAnswerQuestion convert(DataInterpretationMultipleAnswerQuestion question) {
        super.convert(question);
        question.setNumber(getNumber());
        if (getParent() != null)
            question.setParent(getParent().convert(new DataInterpretationSetQuestion()));
        return question;
    }

    public void copy(DataInterpretationMultipleAnswerQuestion question) {
        super.copy(question);
        setNumber(question.getNumber());
    }

    public static DataInterpretationMultipleAnswerQuestionDTO valueOf(DataInterpretationMultipleAnswerQuestion question) {
        if (question == null)
            return null;
        DataInterpretationMultipleAnswerQuestionDTO questionDTO = new DataInterpretationMultipleAnswerQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
