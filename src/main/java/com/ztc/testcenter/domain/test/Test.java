package com.ztc.testcenter.domain.test;

import com.ztc.testcenter.domain.User;
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
@Inheritance(strategy = InheritanceType.JOINED)
public class Test implements Serializable {

    private Long id;
    private Date date;
    private Difficulty difficulty;
    private TestIntelligentType intelligentType = TestIntelligentType.INTELLIGENT;
    private User user;
    private List<TestSection> testSections = new ArrayList<>();

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
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public static enum TestIntelligentType {
        NORMAL,
        INTELLIGENT;
    }
}
