package com.ztc.testcenter.gre.domain.test;

import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.gre.domain.question.Difficulty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Yubar on 2/23/2017.
 */

@Entity
@Table(name = "TESTS")
public class Test implements Serializable {

    private Long id;
    private String username;
    private TestTemplate template;
    private Difficulty difficulty;
    private Date startDate = new Date();
    private Date endDate;
    private List<TestSection> testSections = new ArrayList<>();
    private Boolean free = false;
    private String comment;

    protected Test() {
    }

    public Test(String username, TestTemplate template) {
        setUsername(username);
        setTemplate(template);
    }

    public Test(String username, TestTemplate template, Boolean free) {
        setUsername(username);
        setTemplate(template);
        setFree(free);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date date) {
        this.startDate = date;
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
    @Enumerated
    @Column(nullable = false)
    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @NotNull
    @Column(nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String user) {
        this.username = user;
    }

    @OrderBy("number")
    @OneToMany(mappedBy = "test")
    public List<TestSection> getTestSections() {
        return testSections;
    }

    public void setTestSections(List<TestSection> testSections) {
        this.testSections = testSections;
    }

    @NotNull
    @ManyToOne(optional = false)
    public TestTemplate getTemplate() {
        return template;
    }

    public void setTemplate(TestTemplate template) {
        this.template = template;
    }

    @NotNull
    @Column(nullable = false)
    public Boolean getFree() {
        return free;
    }

    void setFree(Boolean free) {
        if (free == null)
            throw new NullPointerException();
        this.free = free;
    }

    @Column(length = 3000)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
