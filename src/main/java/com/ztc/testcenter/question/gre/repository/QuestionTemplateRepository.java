package com.ztc.testcenter.question.gre.repository;

import com.ztc.testcenter.question.gre.domain.QuestionType;
import com.ztc.testcenter.question.gre.domain.QuestionTemplate;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yubar on 2/28/17.
 */
public interface QuestionTemplateRepository extends JpaRepository<QuestionTemplate, Long> {

    QuestionTemplate findByLabel(String label);

    @EntityGraph(attributePaths = "questionTemplateItems")
    List<QuestionTemplate> findByQuestionType(QuestionType questionType);

}
