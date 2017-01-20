package com.ztc.testcenter.domain.question;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
public class TextCompletionQuestion extends Question {

    List<TextCompletionQuestionItem> items = new ArrayList<>();

    @OneToMany(mappedBy = "question", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    public List<TextCompletionQuestionItem> getItems() {
        return items;
    }

    public void setItems(List<TextCompletionQuestionItem> items) {
        this.items = items;
    }
}
