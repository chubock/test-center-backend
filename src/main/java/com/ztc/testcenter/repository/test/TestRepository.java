package com.ztc.testcenter.repository.test;

import com.ztc.testcenter.domain.user.User;
import com.ztc.testcenter.domain.question.*;
import com.ztc.testcenter.domain.test.Test;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by yubar on 2/28/17.
 */

public interface TestRepository extends JpaRepository<Test, Long> {

    List<Test> findByUser(User user);

    @Query("select t.id from Test t where t.user = :user and t.endDate is null")
    Long findCurrentTestId(@Param("user") User user);

    @Query("select t from Test t join fetch t.testSections join fetch t.template where t.id = :id")
    Test findOneWithSectionsAndTemplate(@Param("id") Long id);

    @Cacheable(cacheNames = "candidate_questions")
    @Query("select q.id from Question q where q.difficulty = :difficulty and q.difficultyLevel = :difficultyLevel and q.questionType = :questionType and q.free = :free and q.id not in (select aq.question.id from AnsweredQuestion aq where aq.user = :user and aq.difficulty = :difficulty and aq.difficultyLevel = :difficultyLevel and aq.questionType = :questionType and aq.free = :free)")
    List<Long> findQuestionIdForTest(@Param("user") User user, @Param("difficulty") Difficulty difficulty, @Param("difficultyLevel") DifficultyLevel difficultyLevel, @Param("questionType") QuestionType questionType, @Param("free") Boolean free, Pageable pageable);

    @Cacheable(cacheNames = "candidate_questions_by_template")
    @Query("select q.id from Question q where q.difficulty = :difficulty and q.template = :questionTemplate and q.free = :free and q.id not in (select distinct aq.questionParent.id from AnsweredQuestion aq where aq.user = :user and aq.difficulty = :difficulty and aq.questionTemplate = :questionTemplate and aq.free = :free)")
    List<Long> findQuestionIdForTest(@Param("user") User user, @Param("difficulty") Difficulty difficulty, @Param("questionTemplate") QuestionTemplate questionTemplate, @Param("free") Boolean free, Pageable pageable);

    @Query("select q.id from Question q where q.difficulty = :difficulty and q.difficultyLevel = :difficultyLevel and q.questionType = :questionType and q.free = :free")
    List<Long> findQuestionIdForTest(@Param("difficulty") Difficulty difficulty, @Param("difficultyLevel") DifficultyLevel difficultyLevel, @Param("questionType") QuestionType questionType, @Param("free") Boolean free, Pageable pageable);

    @Query("select q.id from Question q where q.difficulty = :difficulty and q.template = :questionTemplate and q.free = :free")
    List<Long> findQuestionIdForTest(@Param("difficulty") Difficulty difficulty, @Param("questionTemplate") QuestionTemplate questionTemplate, @Param("free") Boolean free, Pageable pageable);


}
