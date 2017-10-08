package com.ztc.testcenter.gre.repository.test;

import com.ztc.testcenter.gre.domain.question.Difficulty;
import com.ztc.testcenter.gre.domain.question.QuestionType;
import com.ztc.testcenter.gre.domain.test.AnsweredQuestion;
import com.ztc.testcenter.gre.domain.test.SectionType;
import com.ztc.testcenter.gre.domain.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Yubar on 3/3/2017.
 */
public interface AnsweredQuestionRepository extends JpaRepository<AnsweredQuestion, Long>, JpaSpecificationExecutor<AnsweredQuestion> {

}
