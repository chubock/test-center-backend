package com.ztc.testcenter.question.gre.repository;

import com.ztc.testcenter.question.gre.domain.QuantitativeMultipleAnswerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Yubar on 1/19/2017.
 */
public interface QuantitativeMultipleAnswerQuestionRepository extends JpaRepository<QuantitativeMultipleAnswerQuestion, Long>, JpaSpecificationExecutor<QuantitativeMultipleAnswerQuestion> {

    @Query("select q from QuantitativeMultipleAnswerQuestion q where q.id in :ids")
    List<QuantitativeMultipleAnswerQuestion> findAll(@Param("ids") List<Long> ids);

}
