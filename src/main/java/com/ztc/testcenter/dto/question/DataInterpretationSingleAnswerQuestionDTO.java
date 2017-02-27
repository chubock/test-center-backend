package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.DataInterpretationSingleAnswerQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class DataInterpretationSingleAnswerQuestionDTO extends AbstractQuantitativeSingleAnswerQuestionDTO {

    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void convert(DataInterpretationSingleAnswerQuestion question) {
        super.convert(question);
        question.setNumber(getNumber());
    }

    public void copy(DataInterpretationSingleAnswerQuestion question) {
        super.copy(question);
        setNumber(question.getNumber());
    }

    @Override
    public DataInterpretationSingleAnswerQuestion convert() {
        DataInterpretationSingleAnswerQuestion question = new DataInterpretationSingleAnswerQuestion();
        convert(question);
        return question;
    }

    public static DataInterpretationSingleAnswerQuestionDTO valueOf(DataInterpretationSingleAnswerQuestion question) {
        if (question == null)
            return null;
        DataInterpretationSingleAnswerQuestionDTO questionDTO = new DataInterpretationSingleAnswerQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
