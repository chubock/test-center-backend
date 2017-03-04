package com.ztc.testcenter.dto.test;

import com.ztc.testcenter.domain.test.SectionType;
import com.ztc.testcenter.domain.test.TestTemplateItem;
import com.ztc.testcenter.dto.AbstractDTO;

/**
 * Created by Yubar on 3/4/2017.
 */
public class TestTemplateItemDTO extends AbstractDTO<TestTemplateItem> {

    private Long id;
    private Integer number;
    private SectionType sectionType;

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

    public SectionType getSectionType() {
        return sectionType;
    }

    public void setSectionType(SectionType sectionType) {
        this.sectionType = sectionType;
    }

    @Override
    public TestTemplateItem convert() {
        TestTemplateItem testTemplateItem = new TestTemplateItem(getNumber(), getSectionType());
        testTemplateItem.setId(getId());
        return testTemplateItem;
    }

    public static TestTemplateItemDTO valueOf(TestTemplateItem testTemplateItem) {
        if (testTemplateItem == null)
            return null;
        TestTemplateItemDTO testTemplateItemDTO = new TestTemplateItemDTO();
        testTemplateItemDTO.setId(testTemplateItem.getId());
        testTemplateItemDTO.setNumber(testTemplateItem.getNumber());
        testTemplateItemDTO.setSectionType(testTemplateItem.getSectionType());
        return testTemplateItemDTO;
    }
}
