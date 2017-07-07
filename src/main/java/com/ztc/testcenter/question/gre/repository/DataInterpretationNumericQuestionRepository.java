package com.ztc.testcenter.question.gre.repository;

import com.ztc.testcenter.question.gre.domain.DataInterpretationNumericQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Yubar on 4/1/2017.
 */
public interface DataInterpretationNumericQuestionRepository extends JpaRepository<DataInterpretationNumericQuestion, Long> {

    @Query("select q from DataInterpretationNumericQuestion q where q.id in :ids")
    List<DataInterpretationNumericQuestion> findAll(@Param("ids") List<Long> ids);

}
