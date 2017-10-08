package com.ztc.testcenter.gre.domain.result;

/**
 * Created by yubar on 7/7/17.
 */

public class AnsweredQuestionResultItem {

    private String label;
    private Long count;

    public AnsweredQuestionResultItem(String label, Long count) {
        this.label = label;
        this.count = count;
    }

    public String getLabel() {
        return label;
    }

    public Long getCount() {
        return count;
    }
}
