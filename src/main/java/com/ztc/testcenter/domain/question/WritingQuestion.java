package com.ztc.testcenter.domain.question;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * Created by Yubar on 1/18/2017.
 */

@Entity
public class WritingQuestion extends Question {

    private TaskType taskType;

    @Enumerated
    @NotNull
    @Column(nullable = false)
    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }

    @Override
    QuestionType getQuestionType() {
        return QuestionType.GRE_WRITING;
    }

    public static enum TaskType {
        ANALYZE_AND_ISSUE,
        ANALYZE_AND_ARGUMENT
    }
}
