package com.ztc.testcenter.domain.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
public class ReadingComprehensionMultipleAnswerQuestion extends ThreeChoiceQuestion {
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    QuestionType getQuestionType() {
        return QuestionType.GRE_READING_COMPREHENSION_MULTIPLE_ANSWER;
    }
}
