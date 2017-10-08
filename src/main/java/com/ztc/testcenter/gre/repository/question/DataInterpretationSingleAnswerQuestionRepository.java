package com.ztc.testcenter.gre.repository.question;

import com.ztc.testcenter.gre.domain.question.DataInterpretationSingleAnswerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Yubar on 4/1/2017.
 */
public interface DataInterpretationSingleAnswerQuestionRepository extends JpaRepository<DataInterpretationSingleAnswerQuestion, Long> {

    @Query("select q from DataInterpretationSingleAnswerQuestion q where q.id in :ids")
    List<DataInterpretationSingleAnswerQuestion> findAll(@Param("ids") List<Long> ids);

}
