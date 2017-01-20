package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.TextCompletionQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/21/2017.
 */
public class TextCompletionQuestionDTO extends QuestionDTO {

    private List<TextCompletionQuestionItemDTO> items = new ArrayList<>();

    public List<TextCompletionQuestionItemDTO> getItems() {
        return items;
    }

    public void setItems(List<TextCompletionQuestionItemDTO> items) {
        this.items = items;
    }

    void convert(TextCompletionQuestion question) {
        super.convert(question);
        question.getItems().clear();
        getItems().forEach(item -> question.getItems().add(item.convert()));
    }

    @Override
    public TextCompletionQuestion convert() {
        TextCompletionQuestion question = new TextCompletionQuestion();
        convert(question);
        return question;
    }

    void copy(TextCompletionQuestion question) {
        super.copy(question);
        question.getItems().forEach(item -> getItems().add(TextCompletionQuestionItemDTO.valueOf(item)));
    }

    public static TextCompletionQuestionDTO valueOf(TextCompletionQuestion question) {
        if (question == null)
            return null;
        TextCompletionQuestionDTO questionDTO = new TextCompletionQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
