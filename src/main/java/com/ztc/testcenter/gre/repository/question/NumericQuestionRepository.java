package com.ztc.testcenter.gre.repository.question;

import com.ztc.testcenter.gre.domain.question.NumericQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Yubar on 1/19/2017.
 */
public interface NumericQuestionRepository extends JpaRepository<NumericQuestion, Long>, JpaSpecificationExecutor<NumericQuestion> {

    @Query("select q from NumericQuestion q where q.id in :ids")
    List<NumericQuestion> findAll(@Param("ids") List<Long> ids);

}
