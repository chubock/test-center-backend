package com.ztc.testcenter.dto.test;

import com.ztc.testcenter.domain.question.DifficultyLevel;
import com.ztc.testcenter.domain.question.QuestionTemplateItem;
import com.ztc.testcenter.dto.AbstractDTO;

/**
 * Created by Yubar on 3/4/2017.
 */

public class QuestionTemplateItemDTO extends AbstractDTO<QuestionTemplateItem> {

    private Long id;
    private DifficultyLevel difficultyLevel;
    private Integer count = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public QuestionTemplateItem convert() {
        QuestionTemplateItem questionTemplateItem = new QuestionTemplateItem(getDifficultyLevel(), getCount());
        questionTemplateItem.setId(getId());
        return questionTemplateItem;
    }

    public static QuestionTemplateItemDTO valueOf(QuestionTemplateItem questionTemplateItem) {
        if (questionTemplateItem == null)
            return null;
        QuestionTemplateItemDTO questionTemplateItemDTO = new QuestionTemplateItemDTO();
        questionTemplateItem.setId(questionTemplateItem.getId());
        questionTemplateItem.setDifficultyLevel(questionTemplateItem.getDifficultyLevel());
        questionTemplateItem.setCount(questionTemplateItem.getCount());
        return questionTemplateItemDTO;
    }
}
