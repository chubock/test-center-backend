package com.ztc.testcenter.dto.test;

import com.ztc.testcenter.domain.question.Difficulty;
import com.ztc.testcenter.domain.test.SectionTemplate;
import com.ztc.testcenter.domain.test.SectionType;
import com.ztc.testcenter.dto.AbstractDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 3/4/2017.
 */

public class SectionTemplateDTO extends AbstractDTO<SectionTemplate> {

    private Long id;
    private String name;
    private SectionType sectionType;
    private Difficulty difficulty;
    private List<SectionTemplateItemDTO> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SectionType getSectionType() {
        return sectionType;
    }

    public void setSectionType(SectionType sectionType) {
        this.sectionType = sectionType;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<SectionTemplateItemDTO> getItems() {
        return items;
    }

    public void setItems(List<SectionTemplateItemDTO> items) {
        this.items = items;
    }

    @Override
    public SectionTemplate convert() {
        SectionTemplate sectionTemplate = new SectionTemplate();
        sectionTemplate.setId(getId());
        sectionTemplate.setName(getName());
        sectionTemplate.setDifficulty(getDifficulty());
        sectionTemplate.setSectionType(getSectionType());
        getItems().forEach(sectionTemplateItemDTO -> sectionTemplate.getItems().add(sectionTemplateItemDTO.convert()));
        return sectionTemplate;
    }

    public static SectionTemplateDTO valueOf(SectionTemplate sectionTemplate) {
        if (sectionTemplate == null)
            return null;
        SectionTemplateDTO sectionTemplateDTO = new SectionTemplateDTO();
        sectionTemplateDTO.setId(sectionTemplate.getId());
        sectionTemplateDTO.setSectionType(sectionTemplate.getSectionType());
        sectionTemplateDTO.setDifficulty(sectionTemplate.getDifficulty());
        return sectionTemplateDTO;
    }
}
