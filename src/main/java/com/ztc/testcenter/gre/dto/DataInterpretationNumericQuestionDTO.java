package com.ztc.testcenter.gre.dto;

import com.ztc.testcenter.gre.domain.question.DataInterpretationSetQuestion;
import com.ztc.testcenter.gre.domain.question.DataInterpretationNumericQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class DataInterpretationNumericQuestionDTO extends AbstractNumericQuestionDTO<DataInterpretationNumericQuestion> {

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
    public DataInterpretationNumericQuestion convert(DataInterpretationNumericQuestion question) {
        super.convert(question);
        question.setNumber(getNumber());
        if (getParent() != null)
            question.setParent(getParent().convert(new DataInterpretationSetQuestion()));
        return question;
    }

    void copy(DataInterpretationNumericQuestion question) {
        super.copy(question);
        setNumber(question.getNumber());
    }

    public static DataInterpretationNumericQuestionDTO valueOf(DataInterpretationNumericQuestion question) {
        if (question == null)
            return null;
        DataInterpretationNumericQuestionDTO questionDTO = new DataInterpretationNumericQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
