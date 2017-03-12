package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.SelectInPassageQuestion;
import com.ztc.testcenter.domain.test.AnsweredQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class SelectInPassageQuestionDTO extends QuestionDTO {

    private Integer number;
    private Integer answer;
    private Integer selected;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public Integer getSelected() {
        return selected;
    }

    public void setSelected(Integer selected) {
        this.selected = selected;
    }

    void convert(SelectInPassageQuestion question) {
        super.convert(question);
        question.setNumber(getNumber());
        question.setAnswer(getAnswer());
    }

    @Override
    public SelectInPassageQuestion convert() {
        SelectInPassageQuestion question = new SelectInPassageQuestion();
        convert(question);
        return question;
    }

    void copy(SelectInPassageQuestion question) {
        super.copy(question);
        setAnswer(question.getAnswer());
        setNumber(question.getNumber());
    }

    @Override
    public void setAnswer(String answer) {
        if (answer == null)
            return ;
        setSelected(Integer.valueOf(answer));
    }

    public static SelectInPassageQuestionDTO valueOf(SelectInPassageQuestion question) {
        if (question == null)
            return null;
        SelectInPassageQuestionDTO questionDTO = new SelectInPassageQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
