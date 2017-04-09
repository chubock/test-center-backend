package com.ztc.testcenter.domain.test;

import com.ztc.testcenter.domain.user.User;
import com.ztc.testcenter.domain.question.Difficulty;

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
    private User user;
    private TestTemplate template;
    private Difficulty difficulty;
    private TestIntelligentType intelligentType = TestIntelligentType.INTELLIGENT;
    private Type type = Type.GRE;
    private Date startDate = new Date();
    private Date endDate;
    private List<TestSection> testSections = new ArrayList<>();

    protected Test() {
    }

    public Test(User user, TestTemplate template) {
        this.user = user;
        this.template = template;
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
    @Enumerated
    @Column(nullable = false)
    public TestIntelligentType getIntelligentType() {
        return intelligentType;
    }

    public void setIntelligentType(TestIntelligentType intelligentType) {
        this.intelligentType = intelligentType;
    }

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    @Enumerated
    @Column(nullable = false)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum TestIntelligentType {
        NORMAL,
        INTELLIGENT;
    }

    public enum Type {
        GRE
    }
}
