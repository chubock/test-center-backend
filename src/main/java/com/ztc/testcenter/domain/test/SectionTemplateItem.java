package com.ztc.testcenter.domain.test;

import com.ztc.testcenter.domain.question.Difficulty;
import com.ztc.testcenter.domain.question.DifficultyLevel;
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

    private Long id;
    private Integer number;
    private QuestionType questionType;
    private DifficultyLevel difficulty;
    private QuestionTemplate questionTemplate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Enumerated
    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    @Enumerated
    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyLevel difficulty) {
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
