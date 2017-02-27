package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.AbstractQuantitativeMultipleAnswerQuestion;
import com.ztc.testcenter.domain.question.QuantitativeMultipleAnswerQuestion;

/**
 * Created by Yubar on 1/20/2017.
 */
public abstract class AbstractQuantitativeMultipleAnswerQuestionDTO extends ChoicesQuestionDTO {

    private Integer maxAnswerCount = 0;

    public Integer getMaxAnswerCount() {
        return maxAnswerCount;
    }

    public void setMaxAnswerCount(Integer maxAnswerCount) {
        this.maxAnswerCount = maxAnswerCount;
    }

    public void convert(AbstractQuantitativeMultipleAnswerQuestion question) {
        super.convert(question);
        question.setMaxAnswerCount(getMaxAnswerCount());
    }

    public void copy(AbstractQuantitativeMultipleAnswerQuestion question) {
        super.copy(question);
        setMaxAnswerCount(question.getMaxAnswerCount());
    }
}
