package com.ztc.testcenter.dto.test;

import com.ztc.testcenter.domain.test.Test;
import com.ztc.testcenter.domain.test.TestSection;
import com.ztc.testcenter.dto.AbstractDTO;
import com.ztc.testcenter.dto.question.QuestionDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 3/4/2017.
 */

public class TestSectionDTO extends AbstractDTO<TestSection> {

    private Long id;
    private Integer number;
    private TestDTO test;
    private List<QuestionDTO> answeredQuestions = new ArrayList<>();

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

    public TestDTO getTest() {
        return test;
    }

    public void setTest(TestDTO test) {
        this.test = test;
    }

    public List<QuestionDTO> getAnsweredQuestions() {
        return answeredQuestions;
    }

    public void setAnsweredQuestions(List<QuestionDTO> answeredQuestions) {
        this.answeredQuestions = answeredQuestions;
    }

    @Override
    public TestSection convert(TestSection testSection) {
        testSection.setId(getId());
        testSection.setNumber(getNumber());
        if (getTest() != null && testSection.getTest() != null)
            testSection.setTest(getTest().convert(testSection.getTest()));
        return testSection;
    }

    public static TestSectionDTO valueOf(TestSection testSection) {
        if (testSection == null)
            return null;
        TestSectionDTO testSectionDTO = new TestSectionDTO();
        testSectionDTO.setId(testSection.getId());
        testSectionDTO.setNumber(testSection.getNumber());
        return testSectionDTO;
    }
}
