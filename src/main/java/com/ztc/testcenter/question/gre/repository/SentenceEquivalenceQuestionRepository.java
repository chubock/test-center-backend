package com.ztc.testcenter.question.gre.repository;

import com.ztc.testcenter.question.gre.domain.SentenceEquivalenceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Yubar on 1/19/2017.
 */
public interface SentenceEquivalenceQuestionRepository extends JpaRepository<SentenceEquivalenceQuestion, Long>, JpaSpecificationExecutor<SentenceEquivalenceQuestion> {

    @Query("select q from SentenceEquivalenceQuestion q where q.id in :ids")
    List<SentenceEquivalenceQuestion> findAll(@Param("ids") List<Long> ids);

}
