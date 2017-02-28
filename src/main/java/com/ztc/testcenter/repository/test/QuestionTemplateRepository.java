package com.ztc.testcenter.repository.test;

import com.ztc.testcenter.domain.question.QuestionType;
import com.ztc.testcenter.domain.test.QuestionTemplate;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by yubar on 2/28/17.
 */
public interface QuestionTemplateRepository extends JpaRepository<QuestionTemplate, Long> {

    QuestionTemplate findByLabel(String label);

    @EntityGraph(attributePaths = "questionTemplateItems")
    List<QuestionTemplate> findByQuestionType(QuestionType questionType);

}
