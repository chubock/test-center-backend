package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.TextCompletionQuestionItem;
import com.ztc.testcenter.dto.AbstractDTO;

/**
 * Created by Yubar on 1/21/2017.
 */

public class TextCompletionQuestionItemDTO extends AbstractDTO<TextCompletionQuestionItem> {

    private long id;
    private String choice1;
    private String choice2;
    private String choice3;
    private String choice4;
    private String choice5;
    private int answer;
    private TextCompletionQuestionDTO question;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public String getChoice5() {
        return choice5;
    }

    public void setChoice5(String choice5) {
        this.choice5 = choice5;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public TextCompletionQuestionDTO getQuestion() {
        return question;
    }

    public void setQuestion(TextCompletionQuestionDTO question) {
        this.question = question;
    }

    @Override
    public TextCompletionQuestionItem convert() {
        TextCompletionQuestionItem item = new TextCompletionQuestionItem();
        item.setAnswer(getAnswer());
        item.setChoice1(getChoice1());
        item.setChoice2(getChoice2());
        item.setChoice3(getChoice3());
        item.setChoice4(getChoice4());
        item.setChoice5(getChoice5());
        if (getQuestion() != null)
            item.setQuestion(getQuestion().convert());
        return item;
    }

    public static TextCompletionQuestionItemDTO valueOf(TextCompletionQuestionItem item) {
        if (item == null)
            return null;
        TextCompletionQuestionItemDTO itemDTO = new TextCompletionQuestionItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setAnswer(item.getAnswer());
        itemDTO.setChoice1(item.getChoice1());
        itemDTO.setChoice2(item.getChoice2());
        itemDTO.setChoice3(item.getChoice3());
        itemDTO.setChoice4(item.getChoice4());
        itemDTO.setChoice5(item.getChoice5());
        return itemDTO;
    }
}
