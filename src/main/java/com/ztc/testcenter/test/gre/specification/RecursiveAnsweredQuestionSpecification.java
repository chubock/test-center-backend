package com.ztc.testcenter.test.gre.specification;

import com.ztc.testcenter.question.gre.domain.Difficulty;
import com.ztc.testcenter.question.gre.domain.QuestionType;
import com.ztc.testcenter.test.gre.domain.AnsweredQuestion;
import com.ztc.testcenter.test.gre.domain.AnsweredQuestion_;
import com.ztc.testcenter.test.gre.domain.SectionType;
import com.ztc.testcenter.test.gre.domain.Test;
import org.springframework.data.jpa.domain.Specification;
import sun.plugin.dom.exception.InvalidStateException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by yubar on 7/7/17.
 */
public class RecursiveAnsweredQuestionSpecification implements Specification<AnsweredQuestion> {

    private Test test;
    private RecursiveAnsweredQuestionSpecification parent;
    private String title;
    private String value;

    public RecursiveAnsweredQuestionSpecification(Test test, String title, String value) {
        this.test = test;
        this.title = title;
        this.value = value;
    }

    public RecursiveAnsweredQuestionSpecification(RecursiveAnsweredQuestionSpecification parent, String title, String value) {
        this.parent = parent;
        this.test = parent.test;
        this.title = title;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<AnsweredQuestion> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.equal(root.get(AnsweredQuestion_.test), test);
        switch (title) {
            case "status":
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(AnsweredQuestion_.status), AnsweredQuestion.Status.valueOf(value)));
                break;
            case "questionType":
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(AnsweredQuestion_.questionType), QuestionType.valueOf(value)));
                break;
            case "testSectionGroup":
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(AnsweredQuestion_.testSectionGroup), SectionType.Group.valueOf(value)));
                break;
            case "difficulty":
                predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(AnsweredQuestion_.difficulty), Difficulty.valueOf(value)));
        }
        return parent == null ? predicate : criteriaBuilder.and(predicate, parent.toPredicate(root, criteriaQuery, criteriaBuilder));
    }
}
