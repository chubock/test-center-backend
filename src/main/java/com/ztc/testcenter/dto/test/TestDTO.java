package com.ztc.testcenter.dto.test;

import com.ztc.testcenter.domain.question.Difficulty;
import com.ztc.testcenter.domain.test.Test;
import com.ztc.testcenter.dto.AbstractDTO;
import com.ztc.testcenter.dto.UserDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yubar on 3/4/2017.
 */

public abstract class TestDTO extends AbstractDTO<Test> {

    private Long id;
    private Date date = new Date();
    private Difficulty difficulty;
    private Test.TestIntelligentType intelligentType = Test.TestIntelligentType.INTELLIGENT;
    private UserDTO user;
    private List<TestSectionDTO> testSections = new ArrayList<>();
    private TestTemplateDTO template;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Test.TestIntelligentType getIntelligentType() {
        return intelligentType;
    }

    public void setIntelligentType(Test.TestIntelligentType intelligentType) {
        this.intelligentType = intelligentType;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<TestSectionDTO> getTestSections() {
        return testSections;
    }

    public void setTestSections(List<TestSectionDTO> testSections) {
        this.testSections = testSections;
    }

    public TestTemplateDTO getTemplate() {
        return template;
    }

    public void setTemplate(TestTemplateDTO template) {
        this.template = template;
    }

    void convert(Test test) {
        test.setId(getId());
        test.setDifficulty(getDifficulty());
        test.setDate(getDate());
        test.setIntelligentType(getIntelligentType());
        if (getTemplate() != null)
            test.setTemplate(getTemplate().convert());
        getTestSections().forEach(testSectionDTO -> test.getTestSections().add(testSectionDTO.convert()));
    }


    void copy(Test test) {
        setId(test.getId());
        setIntelligentType(test.getIntelligentType());
        setDifficulty(test.getDifficulty());
        setDate(test.getDate());
    }
}
