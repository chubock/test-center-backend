package com.ztc.testcenter.gre.repository.question;

import com.ztc.testcenter.gre.domain.question.DataInterpretationMultipleAnswerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Yubar on 4/1/2017.
 */
public interface DataInterpretationMultipleAnswerQuestionRepository extends JpaRepository<DataInterpretationMultipleAnswerQuestion, Long> {

    @Query("select q from DataInterpretationMultipleAnswerQuestion q where q.id in :ids")
    List<DataInterpretationMultipleAnswerQuestion> findAll(@Param("ids") List<Long> ids);

}
