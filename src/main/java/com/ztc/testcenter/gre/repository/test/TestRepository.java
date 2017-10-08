package com.ztc.testcenter.gre.repository.test;

import com.ztc.testcenter.gre.domain.test.Test;
import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.gre.domain.question.Difficulty;
import com.ztc.testcenter.gre.domain.question.DifficultyLevel;
import com.ztc.testcenter.gre.domain.question.QuestionTemplate;
import com.ztc.testcenter.gre.domain.question.QuestionType;
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

    List<Test> findByUsernameAndEndDateIsNotNull(String username);

    @Query("select t.id from Test t where t.username = :username and t.endDate is null")
    Long getIdByUsernameAndEndDateIsNull(@Param("username") String username);

    @Query("select t from Test t join fetch t.testSections join fetch t.template where t.id = :id")
    Test findOneWithSectionsAndTemplate(@Param("id") Long id);

    @Cacheable(cacheNames = "candidate_questions")
    @Query("select q.id from Question q where q.difficulty = :difficulty and q.difficultyLevel = :difficultyLevel and q.questionType = :questionType and q.free = :free and q.id not in (select aq.question.id from AnsweredQuestion aq where aq.username = :username and aq.difficulty = :difficulty and aq.difficultyLevel = :difficultyLevel and aq.questionType = :questionType and aq.free = :free)")
    List<Long> findQuestionIdForTest(@Param("username") String username, @Param("difficulty") Difficulty difficulty, @Param("difficultyLevel") DifficultyLevel difficultyLevel, @Param("questionType") QuestionType questionType, @Param("free") Boolean free, Pageable pageable);

    @Cacheable(cacheNames = "candidate_questions_by_template")
    @Query("select q.id from Question q where q.difficulty = :difficulty and q.template = :questionTemplate and q.free = :free and q.id not in (select distinct aq.questionParent.id from AnsweredQuestion aq where aq.username = :username and aq.difficulty = :difficulty and aq.questionTemplate = :questionTemplate and aq.free = :free)")
    List<Long> findQuestionIdForTest(@Param("username") String user, @Param("difficulty") Difficulty difficulty, @Param("questionTemplate") QuestionTemplate questionTemplate, @Param("free") Boolean free, Pageable pageable);

    @Query("select q.id from Question q where q.difficulty = :difficulty and q.difficultyLevel = :difficultyLevel and q.questionType = :questionType and q.free = :free")
    List<Long> findQuestionIdForTest(@Param("difficulty") Difficulty difficulty, @Param("difficultyLevel") DifficultyLevel difficultyLevel, @Param("questionType") QuestionType questionType, @Param("free") Boolean free, Pageable pageable);

    @Query("select q.id from Question q where q.difficulty = :difficulty and q.template = :questionTemplate and q.free = :free")
    List<Long> findQuestionIdForTest(@Param("difficulty") Difficulty difficulty, @Param("questionTemplate") QuestionTemplate questionTemplate, @Param("free") Boolean free, Pageable pageable);


}
