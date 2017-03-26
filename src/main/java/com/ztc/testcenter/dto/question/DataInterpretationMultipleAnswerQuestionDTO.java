package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.DataInterpretationMultipleAnswerQuestion;
import com.ztc.testcenter.domain.question.QuantitativeMultipleAnswerQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class DataInterpretationMultipleAnswerQuestionDTO extends AbstractQuantitativeMultipleAnswerQuestionDTO {

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

    public void convert(DataInterpretationMultipleAnswerQuestion question) {
        super.convert(question);
        question.setNumber(getNumber());
        if (getParent() != null)
            question.setParent(getParent().convert());
    }

    public void copy(DataInterpretationMultipleAnswerQuestion question) {
        super.copy(question);
        setNumber(question.getNumber());
    }

    @Override
    public DataInterpretationMultipleAnswerQuestion convert() {
        DataInterpretationMultipleAnswerQuestion question = new DataInterpretationMultipleAnswerQuestion();
        convert(question);
        return question;
    }

    public static DataInterpretationMultipleAnswerQuestionDTO valueOf(DataInterpretationMultipleAnswerQuestion question) {
        if (question == null)
            return null;
        DataInterpretationMultipleAnswerQuestionDTO questionDTO = new DataInterpretationMultipleAnswerQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
