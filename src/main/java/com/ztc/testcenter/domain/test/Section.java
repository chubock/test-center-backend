package com.ztc.testcenter.domain.test;

import com.ztc.testcenter.domain.question.Question;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yubar on 2/27/17.
 */

@Entity
@Table(name = "SECTIONS")
public class Section implements Serializable {

    private Long id;
    private SectionTemplate template;
    private List<Question> questions = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Column(nullable = false)
    public SectionTemplate getTemplate() {
        return template;
    }

    public void setTemplate(SectionTemplate template) {
        this.template = template;
    }

    @OneToMany(mappedBy = "section")
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
