package com.ztc.testcenter.question.gre.specification;

import com.ztc.testcenter.question.gre.domain.Difficulty;
import com.ztc.testcenter.question.gre.domain.DifficultyLevel;
import com.ztc.testcenter.question.gre.domain.Question;
import com.ztc.testcenter.question.gre.domain.QuestionType;
import com.ztc.testcenter.question.gre.domain.Question_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yubar on 6/2/17.
 */

public class QuestionSpecification<T extends Question> implements Specification<T> {

    private Difficulty[] difficulties = new Difficulty[]{};
    private DifficultyLevel[] difficultyLevels = new DifficultyLevel[]{};
    private Boolean free;
    private QuestionType[] questionTypes = new QuestionType[]{};

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

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public QuestionType[] getQuestionTypes() {
        return questionTypes;
    }

    public void setQuestionTypes(QuestionType[] questionTypes) {
        this.questionTypes = questionTypes;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        Predicate difficultyPredicates = null;
        for (Difficulty difficulty : difficulties) {
            if (difficultyPredicates == null)
                difficultyPredicates = criteriaBuilder.equal(root.get(Question_.difficulty), difficulty);
            else
                difficultyPredicates = criteriaBuilder.or(difficultyPredicates, criteriaBuilder.equal(root.get(Question_.difficulty), difficulty));
        }

        Predicate difficultyLevelPredicates = null;
        for (DifficultyLevel difficultyLevel : difficultyLevels) {
            if (difficultyLevelPredicates == null)
                difficultyLevelPredicates = criteriaBuilder.equal(root.get(Question_.difficultyLevel), difficultyLevel);
            else
                difficultyLevelPredicates = criteriaBuilder.or(difficultyLevelPredicates, criteriaBuilder.equal(root.get(Question_.difficultyLevel), difficultyLevel));
        }

        Predicate freePredicate = null;
        if (free != null)
            freePredicate = criteriaBuilder.equal(root.get(Question_.free), free);

        Predicate questionTypePredicates = null;
        for (QuestionType questionType : questionTypes) {
            if (questionTypePredicates == null)
                questionTypePredicates = criteriaBuilder.equal(root.get(Question_.questionType), questionType);
            else
                questionTypePredicates = criteriaBuilder.or(questionTypePredicates, criteriaBuilder.equal(root.get(Question_.questionType), questionType));
        }

        List<Predicate> predicates = new ArrayList<>();
        if (difficultyPredicates != null)
            predicates.add(difficultyPredicates);
        if (difficultyLevelPredicates != null)
            predicates.add(difficultyLevelPredicates);
        if (freePredicate != null)
            predicates.add(freePredicate);
        if (questionTypePredicates != null)
            predicates.add(questionTypePredicates);

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
