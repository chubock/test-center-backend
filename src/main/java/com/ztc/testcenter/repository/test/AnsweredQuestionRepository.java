package com.ztc.testcenter.repository.test;

import com.ztc.testcenter.domain.test.AnsweredQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by Yubar on 3/3/2017.
 */
public interface AnsweredQuestionRepository extends JpaRepository<AnsweredQuestion, Long>, JpaSpecificationExecutor<AnsweredQuestion> {
}
