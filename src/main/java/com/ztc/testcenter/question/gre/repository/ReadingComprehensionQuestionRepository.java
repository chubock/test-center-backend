package com.ztc.testcenter.question.gre.repository;

import com.ztc.testcenter.question.gre.domain.ReadingComprehensionQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Yubar on 2/9/2017.
 */
public interface ReadingComprehensionQuestionRepository extends JpaRepository<ReadingComprehensionQuestion, Long>, JpaSpecificationExecutor<ReadingComprehensionQuestion> {

    @Query("select q from ReadingComprehensionQuestion q where q.id in :ids")
    List<ReadingComprehensionQuestion> findAll(@Param("ids") List<Long> ids);

}
