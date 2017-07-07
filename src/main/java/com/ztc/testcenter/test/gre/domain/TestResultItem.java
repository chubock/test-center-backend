package com.ztc.testcenter.test.gre.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yubar on 7/7/17.
 */

@Entity
public class TestResultItem {

    private Long id;
    private TestResult testResult;
    private String label;
    private Integer value;
    private List<TestResult> testResults = new ArrayList<>();

    protected TestResultItem() {
    }

    public TestResultItem(TestResult testResult, String label, Integer value) {
        this.testResult = testResult;
        this.label = label;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    public TestResult getTestResult() {
        return testResult;
    }

    private void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }

    @Column(nullable = false)
    public String getLabel() {
        return label;
    }

    private void setLabel(String label) {
        this.label = label;
    }

    @Column(nullable = false)
    public Integer getValue() {
        return value;
    }

    private void setValue(Integer value) {
        this.value = value;
    }

    @OneToMany(mappedBy = "parentItem")
    public List<TestResult> getTestResults() {
        return testResults;
    }

    public void setTestResults(List<TestResult> testResults) {
        this.testResults = testResults;
    }
}
