package com.ztc.testcenter.question.gre.dto;

import com.ztc.testcenter.question.gre.domain.TextCompletionQuestion;
import com.ztc.testcenter.question.gre.domain.TextCompletionQuestionItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/21/2017.
 */
public class TextCompletionQuestionDTO extends QuestionDTO<TextCompletionQuestion> {

    private List<TextCompletionQuestionItemDTO> items = new ArrayList<>();

    public List<TextCompletionQuestionItemDTO> getItems() {
        return items;
    }

    public void setItems(List<TextCompletionQuestionItemDTO> items) {
        this.items = items;
    }

    @Override
    public TextCompletionQuestion convert(TextCompletionQuestion question) {
        super.convert(question);
        question.getItems().clear();
        getItems().forEach(itemDTO -> question.getItems().add(itemDTO.convert(new TextCompletionQuestionItem())));
        return question;
    }

    void copy(TextCompletionQuestion question) {
        super.copy(question);
        question.getItems().forEach(item -> getItems().add(TextCompletionQuestionItemDTO.valueOf(item)));
    }

    @Override
    public void setUserAnswer(String answer) {
        if (answer == null)
            return ;
        int i = 0;
        for (Character character: answer.toCharArray()) {
            if (Character.isDigit(character))
                getItems().get(i).getChoices().get(Character.digit(character, 10)).setSelected(true);
            i++;
        }
    }

    public static TextCompletionQuestionDTO valueOf(TextCompletionQuestion question) {
        if (question == null)
            return null;
        TextCompletionQuestionDTO questionDTO = new TextCompletionQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
