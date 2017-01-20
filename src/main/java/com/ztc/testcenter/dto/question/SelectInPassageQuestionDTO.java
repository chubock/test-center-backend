package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.SelectInPassageQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public class SelectInPassageQuestionDTO extends QuestionDTO {

    private int answer;

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    void convert(SelectInPassageQuestion question) {
        super.convert(question);
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
    }

    public static SelectInPassageQuestionDTO valueOf(SelectInPassageQuestion question) {
        if (question == null)
            return null;
        SelectInPassageQuestionDTO questionDTO = new SelectInPassageQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
