package com.ztc.testcenter.dto.test;

import com.ztc.testcenter.domain.test.TestTemplate;
import com.ztc.testcenter.dto.AbstractDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 3/4/2017.
 */
public class TestTemplateDTO extends AbstractDTO<TestTemplate> {

    private Long id;
    private List<TestTemplateItemDTO> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TestTemplateItemDTO> getItems() {
        return items;
    }

    public void setItems(List<TestTemplateItemDTO> items) {
        this.items = items;
    }

    @Override
    public TestTemplate convert() {
        TestTemplate testTemplate = new TestTemplate();
        testTemplate.setId(getId());
        getItems().forEach(testTemplateItemDTO -> testTemplate.getItems().add(testTemplateItemDTO.convert()));
        return testTemplate;
    }

    public static TestTemplateDTO valueOf(TestTemplate testTemplate) {
        if (testTemplate == null)
            return null;
        TestTemplateDTO testTemplateDTO = new TestTemplateDTO();
        testTemplateDTO.setId(testTemplate.getId());
        return testTemplateDTO;
    }
}
