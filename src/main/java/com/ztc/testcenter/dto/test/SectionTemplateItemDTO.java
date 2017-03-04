package com.ztc.testcenter.dto.test;

import com.ztc.testcenter.domain.question.DifficultyLevel;
import com.ztc.testcenter.domain.question.QuestionType;
import com.ztc.testcenter.domain.test.SectionTemplate;
import com.ztc.testcenter.domain.test.SectionTemplateItem;
import com.ztc.testcenter.dto.AbstractDTO;

/**
 * Created by Yubar on 3/4/2017.
 */

public class SectionTemplateItemDTO extends AbstractDTO<SectionTemplateItem> {

    private Long id;
    private Integer number;
    private QuestionType questionType;
    private DifficultyLevel difficulty;
    private QuestionTemplateDTO questionTemplate;

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

    public QuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(QuestionType questionType) {
        this.questionType = questionType;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }

    public QuestionTemplateDTO getQuestionTemplate() {
        return questionTemplate;
    }

    public void setQuestionTemplate(QuestionTemplateDTO questionTemplate) {
        this.questionTemplate = questionTemplate;
    }

    @Override
    public SectionTemplateItem convert() {
        SectionTemplateItem sectionTemplateItem = new SectionTemplateItem();
        sectionTemplateItem.setId(getId());
        sectionTemplateItem.setDifficulty(getDifficulty());
        sectionTemplateItem.setNumber(getNumber());
        sectionTemplateItem.setQuestionType(getQuestionType());
        if (getQuestionTemplate() != null)
            sectionTemplateItem.setQuestionTemplate(getQuestionTemplate().convert());
        return sectionTemplateItem;
    }

    public static SectionTemplateItemDTO valueOf(SectionTemplateItem sectionTemplateItem) {
        if (sectionTemplateItem == null)
            return null;
        SectionTemplateItemDTO sectionTemplateItemDTO = new SectionTemplateItemDTO();
        sectionTemplateItemDTO.setId(sectionTemplateItem.getId());
        sectionTemplateItemDTO.setQuestionType(sectionTemplateItem.getQuestionType());
        sectionTemplateItemDTO.setNumber(sectionTemplateItem.getNumber());
        sectionTemplateItemDTO.setDifficulty(sectionTemplateItem.getDifficulty());
        return sectionTemplateItemDTO;
    }
}
