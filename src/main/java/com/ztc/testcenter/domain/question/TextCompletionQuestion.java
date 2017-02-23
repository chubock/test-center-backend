package com.ztc.testcenter.domain.question;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
public class TextCompletionQuestion extends Question {

    List<TextCompletionQuestionItem> items = new ArrayList<>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    public List<TextCompletionQuestionItem> getItems() {
        return items;
    }

    public void setItems(List<TextCompletionQuestionItem> items) {
        this.items = items;
    }

    @Override
    QuestionType getQuestionType() {
        return QuestionType.GRE_TEXT_COMPLETION;
    }

    @Override
    public void prepare(){
        getItems().forEach(item -> item.prepare());
    }

}
