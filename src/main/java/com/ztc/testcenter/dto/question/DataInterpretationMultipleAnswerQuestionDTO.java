package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.DataInterpretationMultipleAnswerQuestion;
import com.ztc.testcenter.domain.question.QuantitativeMultipleAnswerQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class DataInterpretationMultipleAnswerQuestionDTO extends AbstractQuantitativeMultipleAnswerQuestionDTO {

    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void convert(DataInterpretationMultipleAnswerQuestion question) {
        super.convert(question);
        question.setNumber(getNumber());
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
