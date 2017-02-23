package com.ztc.testcenter.domain.question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Yubar on 1/20/2017.
 */

@Entity
public class ReadingComprehensionMultipleAnswerQuestion extends ThreeChoiceQuestion {
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    QuestionType getQuestionType() {
        return QuestionType.GRE_READING_COMPREHENSION_MULTIPLE_ANSWER;
    }
}
