package com.ztc.testcenter.question.gre.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Yubar on 2/23/2017.
 */

@Entity
@Table(name = "QUESTION_TEMPLATE_ITEMS")
public class QuestionTemplateItem implements Serializable {

    private Long id;
    private DifficultyLevel difficultyLevel;
    private Integer count = 0;

    public QuestionTemplateItem() {
    }

    public QuestionTemplateItem(DifficultyLevel difficultyLevel, Integer count) {
        this.difficultyLevel = difficultyLevel;
        this.count = count;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Enumerated
    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @NotNull
    @Column(nullable = false)
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
