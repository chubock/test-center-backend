package com.ztc.testcenter.repository.question;

import com.ztc.testcenter.domain.user.User;
import com.ztc.testcenter.domain.question.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Yubar on 1/19/2017.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Cacheable(cacheNames = "questions_count")
    @Query("select count(q) from Question q where q.difficulty = :difficulty and q.difficultyLevel = :difficultyLevel and q.questionType = :questionType")
    Long countOfQuestions(@Param("questionType") QuestionType questionType, @Param("difficulty") Difficulty difficulty, @Param("difficultyLevel") DifficultyLevel difficultyLevel);

    @Cacheable(cacheNames = "user_questions_count")
    @Query("select count(q) from Question q where q.difficulty = :difficulty and q.template = :template")
    Long countOfQuestions(@Param("template") QuestionTemplate template, @Param("difficulty") Difficulty difficulty);

    @Cacheable(cacheNames = "questions_count_by_template")
    @Query("select count(q) from AnsweredQuestion q where q.user = :user and q.difficulty = :difficulty and q.difficultyLevel = :difficultyLevel and q.questionType = :questionType")
    Long countOfQuestions(@Param("user") User user, @Param("questionType") QuestionType questionType, @Param("difficulty") Difficulty difficulty, @Param("difficultyLevel") DifficultyLevel difficultyLevel);

    @Cacheable(cacheNames = "user_questions_count_by_template")
    @Query("select distinct count(q.questionParent) from AnsweredQuestion q where q.user = :user and q.difficulty = :difficulty and q.questionTemplate = :template")
    Long countOfQuestions(@Param("user") User user, @Param("template") QuestionTemplate template, @Param("difficulty") Difficulty difficulty);

}
