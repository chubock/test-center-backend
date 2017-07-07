package com.ztc.testcenter.test.gre.repository;

import com.ztc.testcenter.question.gre.domain.Difficulty;
import com.ztc.testcenter.question.gre.domain.QuestionType;
import com.ztc.testcenter.test.gre.domain.AnsweredQuestion;
import com.ztc.testcenter.test.gre.domain.SectionType;
import com.ztc.testcenter.test.gre.domain.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Yubar on 3/3/2017.
 */
public interface AnsweredQuestionRepository extends JpaRepository<AnsweredQuestion, Long>, JpaSpecificationExecutor<AnsweredQuestion> {

    Integer countByTestAndStatus(Test test, AnsweredQuestion.Status status);
    Integer countByTestAndQuestionType(Test test, QuestionType questionType);
    Integer countByTestAndTestSectionGroup(Test test, SectionType.Group group);
    Integer countByTestAndDifficulty(Test test, Difficulty difficulty);

    Integer countByTestAndStatusAndQuestionType(Test test, AnsweredQuestion.Status status, QuestionType questionType);
    Integer countByTestAndStatusAndTestSectionGroup(Test test, AnsweredQuestion.Status status, SectionType.Group group);
    Integer countByTestAndStatusAndDifficulty(Test test, AnsweredQuestion.Status status, Difficulty difficulty);

    Integer countByTestAndQuestionTypeAndTestSectionGroup(Test test, QuestionType questionType, SectionType.Group group);
    Integer countByTestAndQuestionTypeAndDifficulty(Test test, QuestionType questionType, Difficulty difficulty);

    Integer countByTestAndTestSectionGroupAndDifficulty(Test test, SectionType.Group group, Difficulty difficulty);

    Integer countByTestAndStatusAndQuestionTypeAndTestSectionGroup(Test test, AnsweredQuestion.Status status, QuestionType questionType, SectionType.Group group);
    Integer countByTestAndStatusAndQuestionTypeAndDifficulty(Test test, AnsweredQuestion.Status status, QuestionType questionType, Difficulty difficulty);

    Integer countByTestAndQuestionTypeAndTestSectionGroupAndDifficulty(Test test, QuestionType questionType, SectionType.Group group, Difficulty difficulty);

    Integer countByTestAndStatusAndQuestionTypeAndTestSectionGroupAndDifficulty(Test test, AnsweredQuestion.Status status, QuestionType questionType, SectionType.Group group, Difficulty difficulty);

}
