package com.ztc.testcenter.question.gre.repository;

import com.ztc.testcenter.question.gre.domain.ReadingComprehensionMultipleAnswerQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Yubar on 4/1/2017.
 */
public interface ReadingComprehensionMultipleAnswerQuestionRepository extends JpaRepository<ReadingComprehensionMultipleAnswerQuestion, Long> {

    @Query("select q from ReadingComprehensionMultipleAnswerQuestion q where q.id in :ids")
    List<ReadingComprehensionMultipleAnswerQuestion> findAll(@Param("ids") List<Long> ids);

}
