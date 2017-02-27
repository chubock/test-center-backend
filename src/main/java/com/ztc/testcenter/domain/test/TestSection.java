package com.ztc.testcenter.domain.test;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yubar on 2/27/17.
 */

@Entity
@Table(name = "TEST_SECTIONS")
public class TestSection implements Serializable {

    private Long id;
    private Integer number;
    private Test test;
    private SectionTemplate template;
    private List<AnsweredQuestion> answeredQuestions = new ArrayList<>();

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

    @OneToMany(mappedBy = "testSection")
    public List<AnsweredQuestion> getAnsweredQuestions() {
        return answeredQuestions;
    }

    public void setAnsweredQuestions(List<AnsweredQuestion> answeredQuestions) {
        this.answeredQuestions = answeredQuestions;
    }
}
