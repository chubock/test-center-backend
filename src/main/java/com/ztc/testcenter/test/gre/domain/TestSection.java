package com.ztc.testcenter.test.gre.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by yubar on 2/27/17.
 */

@Entity
@Table(name = "TEST_SECTIONS")
public class TestSection implements Serializable {

    private Long id;
    private Integer number;
    private Date startDate = new Date();
    private Date endDate;
    private Test test;
    private SectionTemplate template;
    private SectionType sectionType;
    private List<AnsweredQuestion> answeredQuestions = new ArrayList<>();
    private Date lastActivityDate;
    private Integer lastQuestionNumber = 1;
    private Long remainingSeconds;

    protected TestSection() {
    }

    public TestSection(Test test, SectionTemplate template) {
        this.lastActivityDate = this.startDate;
        this.test = test;
        this.template = template;
        this.sectionType = template.getSectionType();
        this.remainingSeconds = (long) (sectionType.time * 60);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public SectionTemplate getTemplate() {
        return template;
    }

    public void setTemplate(SectionTemplate template) {
        this.template = template;
    }

    public SectionType getSectionType() {
        return sectionType;
    }

    public void setSectionType(SectionType sectionType) {
        this.sectionType = sectionType;
    }

    @OneToMany(mappedBy = "testSection")
    public List<AnsweredQuestion> getAnsweredQuestions() {
        return answeredQuestions;
    }

    public void setAnsweredQuestions(List<AnsweredQuestion> answeredQuestions) {
        this.answeredQuestions = answeredQuestions;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @Column(nullable = false)
    public Date getLastActivityDate() {
        return lastActivityDate;
    }

    public void setLastActivityDate(Date lastActivityDate) {
        if (lastActivityDate == null)
            throw new NullPointerException();
        this.lastActivityDate = lastActivityDate;
    }

    @NotNull
    @Column(nullable = false)
    public Integer getLastQuestionNumber() {
        return lastQuestionNumber;
    }

    public void setLastQuestionNumber(Integer lastQuestionNumber) {
        if (lastQuestionNumber == null)
            throw new NullPointerException();
        this.lastQuestionNumber = lastQuestionNumber;
    }

    @NotNull
    @Column(nullable = false)
    public Long getRemainingSeconds() {
        return remainingSeconds;
    }

    public void setRemainingSeconds(Long remainingSeconds) {
        if (remainingSeconds == null)
            throw new NullPointerException();
        this.remainingSeconds = remainingSeconds;
    }
}
