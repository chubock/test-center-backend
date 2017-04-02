package com.ztc.testcenter.dto.test;

import com.ztc.testcenter.domain.question.Difficulty;
import com.ztc.testcenter.domain.test.SectionType;
import com.ztc.testcenter.domain.test.Test;
import com.ztc.testcenter.dto.AbstractDTO;
import com.ztc.testcenter.dto.UserDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yubar on 3/4/2017.
 */

public class TestDTO extends AbstractDTO<Test> {

    private Long id;
    private Date startDate;
    private Date endDate;
    private Difficulty difficulty;
    private Test.TestIntelligentType intelligentType = Test.TestIntelligentType.INTELLIGENT;
    private UserDTO user;
    private List<TestSectionDTO> testSections = new ArrayList<>();
    private List<SectionType> sectionTypes = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

    public List<SectionType> getSectionTypes() {
        return sectionTypes;
    }

    public void setSectionTypes(List<SectionType> sectionTypes) {
        this.sectionTypes = sectionTypes;
    }

    @Override
    public Test convert(Test test) {
        test.setId(getId());
        test.setDifficulty(getDifficulty());
        test.setStartDate(getStartDate());
        test.setEndDate(getEndDate());
        test.setIntelligentType(getIntelligentType());
        return test;
    }

    void copy(Test test) {
        setId(test.getId());
        setIntelligentType(test.getIntelligentType());
        setDifficulty(test.getDifficulty());
        setStartDate(test.getStartDate());
        setEndDate(test.getEndDate());
    }

    public static TestDTO valueOf(Test test) {
        if (test == null)
            return null;
        TestDTO testDTO = new TestDTO();
        testDTO.copy(test);
        return testDTO;
    }
}
