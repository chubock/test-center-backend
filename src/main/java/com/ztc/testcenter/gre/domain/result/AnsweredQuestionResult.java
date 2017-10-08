package com.ztc.testcenter.gre.domain.result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yubar on 7/20/17.
 */
public class AnsweredQuestionResult {

    private Type type;
    private List<AnsweredQuestionResultItem> items = new ArrayList<>();

    protected AnsweredQuestionResult() {
    }

    public AnsweredQuestionResult(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<AnsweredQuestionResultItem> getItems() {
        return items;
    }

    public void setItems(List<AnsweredQuestionResultItem> items) {
        this.items = items;
    }

    public enum Type {
        CORRECTNESS,
        SECTION_TYPE,
        QUESTION_TYPE,
        DIFFICULTY
    }
}
