package com.ztc.testcenter.repository.question;

import com.ztc.testcenter.domain.question.TextCompletionQuestion;
import com.ztc.testcenter.domain.question.WritingQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yubar on 1/19/2017.
 */
public interface WritingQuestionRepository extends JpaRepository<WritingQuestion, Long> {
}
