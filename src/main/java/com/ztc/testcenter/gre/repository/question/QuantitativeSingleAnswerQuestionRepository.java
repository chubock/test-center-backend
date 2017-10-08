package com.ztc.testcenter.gre.repository.question;

import com.ztc.testcenter.gre.domain.question.QuantitativeSingleAnswerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Yubar on 1/19/2017.
 */
public interface QuantitativeSingleAnswerQuestionRepository extends JpaRepository<QuantitativeSingleAnswerQuestion, Long>, JpaSpecificationExecutor<QuantitativeSingleAnswerQuestion> {

    @Query("select q from QuantitativeSingleAnswerQuestion q where q.id in :ids")
    List<QuantitativeSingleAnswerQuestion> findAll(@Param("ids") List<Long> ids);

}
