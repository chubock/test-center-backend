package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.DataInterpretationNumericQuestion;
import com.ztc.testcenter.domain.question.NumericQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class DataInterpretationNumericQuestionDTO extends AbstractNumericQuestionDTO {

    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    void convert(DataInterpretationNumericQuestion question) {
        super.convert(question);
        question.setNumber(getNumber());
    }

    @Override
    public DataInterpretationNumericQuestion convert() {
        DataInterpretationNumericQuestion question = new DataInterpretationNumericQuestion();
        convert(question);
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
