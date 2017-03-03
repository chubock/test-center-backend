package com.ztc.testcenter.generator;

import com.ztc.testcenter.domain.test.SectionType;
import com.ztc.testcenter.domain.test.TestTemplate;
import com.ztc.testcenter.domain.test.TestTemplateItem;
import com.ztc.testcenter.repository.test.TestTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yubar on 3/2/2017.
 */

@Service
public class TestTemplatesGenerator {

    private final TestTemplateRepository repository;

    @Autowired
    public TestTemplatesGenerator(TestTemplateRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void createTestTemplate1() {
        TestTemplate testTemplate = new TestTemplate();
        int number = 1;
        testTemplate.getItems().add(new TestTemplateItem(number++, SectionType.GRE_ANALYTICAL_WRITING_ARGUMENT));
        testTemplate.getItems().add(new TestTemplateItem(number++, SectionType.GRE_ANALYTICAL_WRITING_ISSUE));
        testTemplate.getItems().add(new TestTemplateItem(number++, SectionType.GRE_VERBAL_REASONING_1));
        testTemplate.getItems().add(new TestTemplateItem(number++, SectionType.GRE_QUANTITATIVE_REASONING_1));
        testTemplate.getItems().add(new TestTemplateItem(number++, SectionType.GRE_QUANTITATIVE_UNSCORE));
        testTemplate.getItems().add(new TestTemplateItem(number++, SectionType.GRE_VERBAL_REASONING_2));
        testTemplate.getItems().add(new TestTemplateItem(number++, SectionType.GRE_QUANTITATIVE_REASONING_2));
        repository.save(testTemplate);
    }

    @Transactional
    public void createTestTemplate2() {
        TestTemplate testTemplate = new TestTemplate();
        int number = 1;
        testTemplate.getItems().add(new TestTemplateItem(number++, SectionType.GRE_ANALYTICAL_WRITING_ARGUMENT));
        testTemplate.getItems().add(new TestTemplateItem(number++, SectionType.GRE_ANALYTICAL_WRITING_ISSUE));
        testTemplate.getItems().add(new TestTemplateItem(number++, SectionType.GRE_QUANTITATIVE_REASONING_1));
        testTemplate.getItems().add(new TestTemplateItem(number++, SectionType.GRE_VERBAL_REASONING_1));
        testTemplate.getItems().add(new TestTemplateItem(number++, SectionType.GRE_VERBAL_UNSCORE));
        testTemplate.getItems().add(new TestTemplateItem(number++, SectionType.GRE_QUANTITATIVE_REASONING_2));
        testTemplate.getItems().add(new TestTemplateItem(number++, SectionType.GRE_VERBAL_REASONING_2));
        repository.save(testTemplate);
    }

    public void createAll() {
        createTestTemplate1();
        createTestTemplate2();
    }
}
