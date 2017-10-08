package com.ztc.testcenter.gre.repository.question;

import com.ztc.testcenter.gre.domain.question.ReadingComprehensionQuestion;
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
