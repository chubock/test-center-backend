package com.ztc.testcenter.question.gre.repository;

import com.ztc.testcenter.question.gre.domain.TextCompletionQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Yubar on 1/19/2017.
 */
public interface TextCompletionQuestionRepository extends JpaRepository<TextCompletionQuestion, Long>, JpaSpecificationExecutor<TextCompletionQuestion> {

    @Query("select q from TextCompletionQuestion q where q.id in :ids")
    List<TextCompletionQuestion> findAll(@Param("ids") List<Long> ids);

    @Override
    @EntityGraph(attributePaths = "items")
    Page<TextCompletionQuestion> findAll(Specification<TextCompletionQuestion> specification, Pageable pageable);

}
