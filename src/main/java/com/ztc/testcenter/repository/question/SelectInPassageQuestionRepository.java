package com.ztc.testcenter.repository.question;

import com.ztc.testcenter.domain.question.SelectInPassageQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Yubar on 4/1/2017.
 */
public interface SelectInPassageQuestionRepository extends JpaRepository<SelectInPassageQuestion, Long> {

    @Query("select q from SelectInPassageQuestion q where q.id in :ids")
    List<SelectInPassageQuestion> findAll(@Param("ids") List<Long> ids);

}
