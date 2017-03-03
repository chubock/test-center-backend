package com.ztc.testcenter.repository.test;

import com.ztc.testcenter.domain.User;
import com.ztc.testcenter.domain.question.*;
import com.ztc.testcenter.domain.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by yubar on 2/28/17.
 */
public interface TestRepository extends JpaRepository<Test, Long> {

    @Query("select q.id from Question q where q.difficulty = :difficulty and q.difficultyLevel = :difficultyLevel and q.questionType = :questionType and q not in (select aq.question from AnsweredQuestion aq where aq.user = :user and aq.difficulty = :difficulty and aq.difficultyLevel = :difficultyLevel and aq.questionType = :questionType)")
    List<Long> findQuestionForTest(User user, Difficulty difficulty, DifficultyLevel difficultyLevel, QuestionType questionType);

    @Query("select q.id from Question q where q.difficulty = :difficulty and q.template = :template and q not in (select aq.question from AnsweredQuestion aq where aq.user = :user and aq.difficulty = :difficulty and aq.questionTemplate = :template)")
    List<Long> findQuestionForTest(User user, Difficulty difficulty, QuestionTemplate questionTemplate);

}
