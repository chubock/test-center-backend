package com.ztc.testcenter.dto.test;

import com.ztc.testcenter.domain.question.Difficulty;
import com.ztc.testcenter.domain.question.QuestionTemplate;
import com.ztc.testcenter.domain.question.QuestionType;
import com.ztc.testcenter.dto.AbstractDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 3/4/2017.
 */

public class QuestionTemplateDTO extends AbstractDTO<QuestionTemplate> {

    private Long id;
    private QuestionType questionType;
    private Difficulty difficulty;
    private List<QuestionTemplateItemDTO> questionTemplateItems = new ArrayList<>();
    private String label;
    private Integer count = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<QuestionTemplateItemDTO> getQuestionTemplateItems() {
        return questionTemplateItems;
    }

    public void setQuestionTemplateItems(List<QuestionTemplateItemDTO> questionTemplateItems) {
        this.questionTemplateItems = questionTemplateItems;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public QuestionTemplate convert() {
        QuestionTemplate questionTemplate = new QuestionTemplate();
        questionTemplate.setId(getId());
        questionTemplate.setQuestionType(getQuestionType());
        questionTemplate.setCount(getCount());
        questionTemplate.setDifficulty(getDifficulty());
        questionTemplate.setLabel(getLabel());
        getQuestionTemplateItems().forEach(questionTemplateItemDTO -> questionTemplate.getQuestionTemplateItems().add(questionTemplateItemDTO.convert()));
        return questionTemplate;
    }
}
