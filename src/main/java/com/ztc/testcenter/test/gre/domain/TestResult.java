package com.ztc.testcenter.test.gre.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yubar on 7/7/17.
 */

@Entity
public class TestResult {

    private Long id;
    private Type type;
    private Test test;
    private List<TestResultItem> items = new ArrayList<>();
    private TestResultItem parentItem;

    protected TestResult() {
    }

    public TestResult(Type type, Test test) {
        this.type = type;
        this.test = test;
    }

    public TestResult(Type type, TestResultItem parentItem) {
        this.type = type;
        this.test = parentItem.getTestResult().getTest();
        this.parentItem = parentItem;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @Enumerated
    public Type getType() {
        return type;
    }

    private void setType(Type type) {
        this.type = type;
    }

    @ManyToOne(optional = false)
    public Test getTest() {
        return test;
    }

    private void setTest(Test test) {
        this.test = test;
    }

    @OneToMany(mappedBy = "testResult")
    public List<TestResultItem> getItems() {
        return items;
    }

    private void setItems(List<TestResultItem> items) {
        this.items = items;
    }

    @ManyToOne
    public TestResultItem getParentItem() {
        return parentItem;
    }

    private void setParentItem(TestResultItem parentItem) {
        this.parentItem = parentItem;
    }

    public enum Type {
        CORRECTNESS,
        SECTION_TYPE,
        DIFFICULTY,
        QUESTION_TYPE
    }
}
