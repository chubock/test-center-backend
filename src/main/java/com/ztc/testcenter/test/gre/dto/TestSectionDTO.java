package com.ztc.testcenter.test.gre.dto;

import com.ztc.testcenter.test.gre.domain.TestSection;
import com.ztc.testcenter.config.dto.AbstractDTO;
import com.ztc.testcenter.question.gre.dto.QuestionDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yubar on 3/4/2017.
 */

public class TestSectionDTO extends AbstractDTO<TestSection> {

    private Long id;
    private Integer number;
    private TestDTO test;
    private List<QuestionDTO> answeredQuestions = new ArrayList<>();
    private Date lastActivityDate;
    private Integer lastQuestionNumber = 1;
    private Long remainingSeconds;

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

    public Date getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        this.lastActivityDate = lastActivityDate;
    }

    public Long getRemainingSeconds() {
        return remainingSeconds;
    }

    public void setRemainingSeconds(Long remainingSeconds) {
        this.remainingSeconds = remainingSeconds;
    }

    public Integer getLastQuestionNumber() {
        return lastQuestionNumber;
    }

    public void setLastQuestionNumber(Integer lastQuestionNumber) {
        this.lastQuestionNumber = lastQuestionNumber;
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
        testSectionDTO.setLastQuestionNumber(testSection.getLastQuestionNumber());
        testSectionDTO.setRemainingSeconds(testSection.getRemainingSeconds());
        testSectionDTO.setLastActivityDate(testSection.getLastActivityDate());
        return testSectionDTO;
    }
}
