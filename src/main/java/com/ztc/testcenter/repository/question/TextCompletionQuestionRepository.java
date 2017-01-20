package com.ztc.testcenter.repository.question;

import com.ztc.testcenter.domain.question.SelectInPassageQuestion;
import com.ztc.testcenter.domain.question.TextCompletionQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yubar on 1/19/2017.
 */
public interface TextCompletionQuestionRepository extends JpaRepository<TextCompletionQuestion, Long> {
}
