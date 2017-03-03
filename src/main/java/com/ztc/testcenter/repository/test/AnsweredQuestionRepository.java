package com.ztc.testcenter.repository.test;

import com.ztc.testcenter.domain.test.AnsweredQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yubar on 3/3/2017.
 */
public interface AnsweredQuestionRepository extends JpaRepository<AnsweredQuestion, Long> {
}
