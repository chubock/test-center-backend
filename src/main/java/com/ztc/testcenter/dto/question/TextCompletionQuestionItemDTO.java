package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.Choice;
import com.ztc.testcenter.domain.question.TextCompletionQuestionItem;
import com.ztc.testcenter.dto.AbstractDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/21/2017.
 */

public class TextCompletionQuestionItemDTO extends AbstractDTO<TextCompletionQuestionItem> {

    private long id;
    private List<Choice> choices = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    @Override
    public TextCompletionQuestionItem convert() {
        TextCompletionQuestionItem item = new TextCompletionQuestionItem();
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
