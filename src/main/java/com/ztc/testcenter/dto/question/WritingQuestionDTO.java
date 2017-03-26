package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.WritingQuestion;

/**
 * Created by Yubar on 1/19/2017.
 */
public class WritingQuestionDTO extends QuestionDTO {

    private WritingQuestion.Type type;
    private String answer;

    public WritingQuestion.Type getType() {
        return type;
    }

    public void setType(WritingQuestion.Type type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public void setUserAnswer(String answer) {
        this.answer = answer;
    }

    public WritingQuestion convert(WritingQuestion question) {
        super.convert(question);
        question.setType(getType());
        return question;
    }

    @Override
    public WritingQuestion convert() {
        WritingQuestion question = new WritingQuestion();
        convert(question);
        return question;
    }

    public void copy(WritingQuestion question) {
        super.copy(question);
        setType(question.getType());
    }

    public static WritingQuestionDTO valueOf(WritingQuestion question) {
        if (question == null)
            return null;
        WritingQuestionDTO questionDTO = new WritingQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
