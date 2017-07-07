package com.ztc.testcenter.test.gre.specification;

import com.ztc.testcenter.question.gre.domain.Difficulty;
import com.ztc.testcenter.question.gre.domain.DifficultyLevel;
import com.ztc.testcenter.question.gre.domain.QuestionType;
import com.ztc.testcenter.test.gre.domain.AnsweredQuestion;
import com.ztc.testcenter.test.gre.domain.AnsweredQuestion_;
import com.ztc.testcenter.user.domain.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yubar on 5/29/17.
 */
public class AnsweredQuestionSpecification implements Specification<AnsweredQuestion> {

    private User user;
    private Difficulty[] difficulties = new Difficulty[]{};
    private DifficultyLevel[] difficultyLevels = new DifficultyLevel[]{};
    private Boolean marked;
    private AnsweredQuestion.Status[] statuses = new AnsweredQuestion.Status[]{};
    private QuestionType[] questionTypes = new QuestionType[]{};

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Difficulty[] getDifficulties() {
        return difficulties;
    }

    public void setDifficulties(Difficulty[] difficulties) {
        this.difficulties = difficulties;
    }

    public DifficultyLevel[] getDifficultyLevels() {
        return difficultyLevels;
    }

    public void setDifficultyLevels(DifficultyLevel[] difficultyLevels) {
        this.difficultyLevels = difficultyLevels;
    }

    public Boolean getMarked() {
        return marked;
    }

    public void setMarked(Boolean marked) {
        this.marked = marked;
    }

    public AnsweredQuestion.Status[] getStatuses() {
        return statuses;
    }

    public void setStatuses(AnsweredQuestion.Status[] statuses) {
        this.statuses = statuses;
    }

    public QuestionType[] getQuestionTypes() {
        return questionTypes;
    }

    public void setQuestionTypes(QuestionType[] questionTypes) {
        this.questionTypes = questionTypes;
    }

    @Override
    public Predicate toPredicate(Root<AnsweredQuestion> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        Predicate userPredicate = null;
        if (user != null)
            userPredicate = criteriaBuilder.equal(root.get(AnsweredQuestion_.user), user);

        Predicate difficultyPredicates = null;
        for (Difficulty difficulty : difficulties) {
            if (difficultyPredicates == null)
                difficultyPredicates = criteriaBuilder.equal(root.get(AnsweredQuestion_.difficulty), difficulty);
            else
                difficultyPredicates = criteriaBuilder.or(difficultyPredicates, criteriaBuilder.equal(root.get(AnsweredQuestion_.difficulty), difficulty));
        }

        Predicate difficultyLevelPredicates = null;
        for (DifficultyLevel difficultyLevel : difficultyLevels) {
            if (difficultyLevelPredicates == null)
                difficultyLevelPredicates = criteriaBuilder.equal(root.get(AnsweredQuestion_.difficultyLevel), difficultyLevel);
            else
                difficultyLevelPredicates = criteriaBuilder.or(difficultyLevelPredicates, criteriaBuilder.equal(root.get(AnsweredQuestion_.difficultyLevel), difficultyLevel));
        }

        Predicate markedPredicate = null;
        if (marked != null)
            markedPredicate = criteriaBuilder.equal(root.get(AnsweredQuestion_.marked), marked);

        Predicate statusesPredicate = null;
        for (AnsweredQuestion.Status status : statuses) {
            if (statusesPredicate == null)
                statusesPredicate = criteriaBuilder.equal(root.get(AnsweredQuestion_.status), status);
            else
                statusesPredicate = criteriaBuilder.or(statusesPredicate, criteriaBuilder.equal(root.get(AnsweredQuestion_.status), status));
        }

        Predicate questionTypesPredicate = null;
        for (QuestionType questionType : questionTypes) {
            if (questionTypesPredicate == null)
                questionTypesPredicate = criteriaBuilder.equal(root.get(AnsweredQuestion_.questionType), questionType);
            else
                questionTypesPredicate = criteriaBuilder.or(questionTypesPredicate, criteriaBuilder.equal(root.get(AnsweredQuestion_.questionType), questionType));
        }

        List<Predicate> predicates = new ArrayList<>();
        if (userPredicate != null)
            predicates.add(userPredicate);
        if (difficultyPredicates != null)
            predicates.add(difficultyPredicates);
        if (difficultyLevelPredicates != null)
            predicates.add(difficultyLevelPredicates);
        if (markedPredicate != null)
            predicates.add(markedPredicate);
        if (questionTypesPredicate != null)
            predicates.add(questionTypesPredicate);
        if (statusesPredicate != null)
            predicates.add(statusesPredicate);

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
