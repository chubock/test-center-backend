package com.ztc.testcenter.gre.domain.question;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * Created by Yubar on 1/18/2017.
 */

@Entity
public class WritingQuestion extends Question {

    private Type type;
    private String description;

    @Enumerated
    @NotNull
    @Column(nullable = false)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Column(length = 3000)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public QuestionType getQuestionType() {
        if (type == Type.ANALYZE_AND_ISSUE)
            return QuestionType.GRE_WRITING_ISSUE;
        else if (type == Type.ANALYZE_AND_ARGUMENT)
            return QuestionType.GRE_WRITING_ARGUMENT;
        return null;
    }

    @Override
    public boolean isCorrect(String answers) {
        return answers != null && !answers.equals("");
    }

    public enum Type {
        ANALYZE_AND_ISSUE,
        ANALYZE_AND_ARGUMENT
    }
}
