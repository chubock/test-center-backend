package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.WritingQuestion;

/**
 * Created by Yubar on 1/19/2017.
 */
public class WritingQuestionDTO extends QuestionDTO {

    private WritingQuestion.TaskType taskType;

    public WritingQuestion.TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(WritingQuestion.TaskType taskType) {
        this.taskType = taskType;
    }

    public WritingQuestion convert(WritingQuestion question) {
        super.convert(question);
        question.setTaskType(getTaskType());
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
        setTaskType(question.getTaskType());
    }

    public static WritingQuestionDTO valueOf(WritingQuestion question) {
        if (question == null)
            return null;
        WritingQuestionDTO questionDTO = new WritingQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
