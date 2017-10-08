package com.ztc.testcenter.gre.repository.question;

import com.ztc.testcenter.gre.domain.question.WritingQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Yubar on 1/19/2017.
 */
public interface WritingQuestionRepository extends JpaRepository<WritingQuestion, Long>, JpaSpecificationExecutor<WritingQuestion> {

    @Query("select w from WritingQuestion w where w.id in :ids")
    List<WritingQuestion> findAll(@Param("ids") List<Long> ids);

}
