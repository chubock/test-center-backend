package com.ztc.testcenter.repository.question;

import com.ztc.testcenter.domain.User;
import com.ztc.testcenter.domain.question.Difficulty;
import com.ztc.testcenter.domain.question.DifficultyLevel;
import com.ztc.testcenter.domain.question.Question;
import com.ztc.testcenter.domain.question.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Yubar on 1/19/2017.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
