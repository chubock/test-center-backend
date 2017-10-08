package com.ztc.testcenter.gre.specification;

import com.ztc.testcenter.gre.domain.question.Difficulty;
import com.ztc.testcenter.gre.domain.question.QuestionType;
import com.ztc.testcenter.gre.domain.test.AnsweredQuestion;
import com.ztc.testcenter.gre.domain.result.AnsweredQuestionResult;
import com.ztc.testcenter.gre.domain.test.AnsweredQuestion_;
import com.ztc.testcenter.gre.domain.test.SectionType;
import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.user.domain.User_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yubar on 7/7/17.
 */
public class TestResultSpecification implements Specification<AnsweredQuestion> {

    private String username;
    private Long testId;
    private List<AnsweredQuestionResult.Type> types = new ArrayList<>();
    private List<String> values = new ArrayList<>();

    public TestResultSpecification() {
    }

    public TestResultSpecification(TestResultSpecification specification, AnsweredQuestionResult.Type type, String value) {
        this.testId = specification.testId;
        this.types.addAll(specification.types);
        this.values.addAll(specification.values);
        this.types.add(type);
        this.values.add(value);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public List<AnsweredQuestionResult.Type> getTypes() {
        return types;
    }

    public List<String> getValues() {
        return values;
    }

    public void setTypes(List<AnsweredQuestionResult.Type> types) {
        this.types = types;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public Predicate toPredicate(Root<AnsweredQuestion> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

        Predicate predicate = criteriaBuilder.equal(root.get(AnsweredQuestion_.username), username);
        if (testId != null)
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get(AnsweredQuestion_.test), testId));
        for (int i = 0; i< types.size(); i++)
            predicate = criteriaBuilder.and(predicate, getPredicate(root, criteriaQuery, criteriaBuilder, types.get(i), values.get(i)));
        return predicate;
    }

    private Predicate getPredicate(Root<AnsweredQuestion> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder, AnsweredQuestionResult.Type type, String value) {
        switch (type) {
            case CORRECTNESS:
                return criteriaBuilder.equal(root.get(AnsweredQuestion_.status), AnsweredQuestion.Status.valueOf(value));
            case SECTION_TYPE:
                return criteriaBuilder.equal(root.get(AnsweredQuestion_.testSectionGroup), SectionType.Group.valueOf(value));
            case DIFFICULTY:
                return criteriaBuilder.equal(root.get(AnsweredQuestion_.difficulty), Difficulty.valueOf(value));
            case QUESTION_TYPE:
                return criteriaBuilder.equal(root.get(AnsweredQuestion_.questionType), QuestionType.valueOf(value));
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestResultSpecification that = (TestResultSpecification) o;

        if (testId != null ? !testId.equals(that.testId) : that.testId != null) return false;
        if (types != null ? !types.equals(that.types) : that.types != null) return false;
        return values != null ? values.equals(that.values) : that.values == null;
    }

    @Override
    public int hashCode() {
        int result = testId != null ? testId.hashCode() : 0;
        result = 31 * result + (types != null ? types.hashCode() : 0);
        result = 31 * result + (values != null ? values.hashCode() : 0);
        return result;
    }
}
