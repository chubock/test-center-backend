package com.ztc.testcenter.repository.question;

import com.ztc.testcenter.domain.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yubar on 1/19/2017.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
