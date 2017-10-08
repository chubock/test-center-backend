package com.ztc.testcenter.gre.domain.question;

import javax.persistence.*;

/**
 * Created by Yubar on 1/18/2017.
 */

@Entity
public class SentenceEquivalenceQuestion extends SixChoiceQuestion {
    @Override
    public QuestionType getQuestionType() {
        return QuestionType.GRE_SENTENCE_EQUIVALENCE;
    }
}
