package com.ztc.testcenter.repository.question;

import com.ztc.testcenter.domain.question.SelectInPassageQuestion;
import com.ztc.testcenter.domain.question.TextCompletionQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Yubar on 1/19/2017.
 */
public interface TextCompletionQuestionRepository extends JpaRepository<TextCompletionQuestion, Long> {

    @EntityGraph(attributePaths = "items")
    @Query(value = "select q from TextCompletionQuestion q")
    Page<TextCompletionQuestion> findAllWithItems(Pageable pageable);

}
