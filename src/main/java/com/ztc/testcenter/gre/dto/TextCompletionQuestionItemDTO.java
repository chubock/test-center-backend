package com.ztc.testcenter.gre.dto;

import com.ztc.testcenter.gre.domain.question.Choice;
import com.ztc.testcenter.gre.domain.question.TextCompletionQuestionItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/21/2017.
 */

public class TextCompletionQuestionItemDTO {

    private Long id;
    private List<Choice> choices = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public TextCompletionQuestionItem convert(TextCompletionQuestionItem item) {
        getChoices().forEach(choice -> item.getChoices().add(choice.clone()));
        return item;
    }

    public static TextCompletionQuestionItemDTO valueOf(TextCompletionQuestionItem item) {
        if (item == null)
            return null;
        TextCompletionQuestionItemDTO itemDTO = new TextCompletionQuestionItemDTO();
        itemDTO.setId(item.getId());
        item.getChoices().forEach(choice -> itemDTO.getChoices().add(choice.clone()));
        return itemDTO;
    }
}
