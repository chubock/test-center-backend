package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.ReadingComprehensionQuestion;
import com.ztc.testcenter.domain.question.SelectInPassageQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class SelectInPassageQuestionDTO extends QuestionDTO<SelectInPassageQuestion> {

    private Integer number;
    private Integer answer;
    private Integer selected;
    private ReadingComprehensionQuestionDTO parent;

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

    public ReadingComprehensionQuestionDTO getParent() {
        return parent;
    }

    public void setParent(ReadingComprehensionQuestionDTO parent) {
        this.parent = parent;
    }

    public SelectInPassageQuestion convert(SelectInPassageQuestion question) {
        super.convert(question);
        question.setNumber(getNumber());
        question.setAnswer(getAnswer());
        if (getParent() != null)
            question.setParent(getParent().convert(new ReadingComprehensionQuestion()));
        return question;
    }

    void copy(SelectInPassageQuestion question) {
        super.copy(question);
        setAnswer(question.getAnswer());
        setNumber(question.getNumber());
    }

    @Override
    public void setUserAnswer(String answer) {
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
