package com.ztc.testcenter.domain.test;

import com.ztc.testcenter.domain.question.Difficulty;
import com.ztc.testcenter.domain.question.QuestionType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Yubar on 2/23/2017.
 */

@Entity
@Table(name = "SECTION_TEMPLATE_ITEMS")
public class SectionTemplateItem implements Serializable {

    private long id;
    private int number;
    private QuestionType questionType;
    private Difficulty difficulty;
    private QuestionTemplate questionTemplate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @NotNull
    @Enumerated
    @Column(nullable = false)
    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    @NotNull
    @Enumerated
    @Column(nullable = false)
    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public QuestionTemplate getQuestionTemplate() {
        return questionTemplate;
    }

    public void setQuestionTemplate(QuestionTemplate questionTemplate) {
        this.questionTemplate = questionTemplate;
    }
}
