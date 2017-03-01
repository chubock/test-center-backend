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
        switch (items.size()) {
            case 1:
                return QuestionType.GRE_TEXT_COMPLETION_ONE_BLANK;
            case 2:
                return QuestionType.GRE_TEXT_COMPLETION_TWO_BLANK;
            case 3:
                return QuestionType.GRE_TEXT_COMPLETION_THREE_BLANK;
        }
        return null;
    }

    @Override
    public void prepare(){
        getItems().forEach(item -> item.prepare());
    }

}
