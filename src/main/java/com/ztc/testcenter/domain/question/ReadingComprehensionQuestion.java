package com.ztc.testcenter.domain.question;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Yubar on 2/9/2017.
 */

@Entity
public class ReadingComprehensionQuestion extends Question implements QuestionsContainer {

    private Type type = Type.MEDIUM;
    private List<ReadingComprehensionSingleAnswerQuestion> singleAnswerQuestions = new ArrayList<>();
    private List<ReadingComprehensionMultipleAnswerQuestion> multipleAnswerQuestions = new ArrayList<>();
    private List<SelectInPassageQuestion> selectInPassageQuestions = new ArrayList<>();

    @Enumerated
    @Column(nullable = false)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "question")
    public List<ReadingComprehensionSingleAnswerQuestion> getSingleAnswerQuestions() {
        return singleAnswerQuestions;
    }

    public void setSingleAnswerQuestions(List<ReadingComprehensionSingleAnswerQuestion> singleAnswerQuestions) {
        this.singleAnswerQuestions = singleAnswerQuestions;
    }

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "question")
    public List<ReadingComprehensionMultipleAnswerQuestion> getMultipleAnswerQuestions() {
        return multipleAnswerQuestions;
    }

    public void setMultipleAnswerQuestions(List<ReadingComprehensionMultipleAnswerQuestion> multipleAnswerQuestions) {
        this.multipleAnswerQuestions = multipleAnswerQuestions;
    }

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "question")
    public List<SelectInPassageQuestion> getSelectInPassageQuestions() {
        return selectInPassageQuestions;
    }

    public void setSelectInPassageQuestions(List<SelectInPassageQuestion> selectInPassageQuestions) {
        this.selectInPassageQuestions = selectInPassageQuestions;
    }

    @Override
    public List<Question> innerQuestions() {
        List<Question> ret = new ArrayList<>(getMultipleAnswerQuestions());
        ret.addAll(getSingleAnswerQuestions());
        ret.addAll(getSelectInPassageQuestions());
        return ret;
    }

    @Override
    public QuestionType getQuestionType() {
        switch (type) {
            case SHORT:
                return QuestionType.GRE_READING_COMPREHENSION_SHORT;
            case MEDIUM:
                return QuestionType.GRE_READING_COMPREHENSION_MEDIUM;
            case LONG:
                return QuestionType.GRE_READING_COMPREHENSION_LONG;
            case PARAGRAPH_ARGUMENT:
                return QuestionType.GRE_READING_COMPREHENSION_PARAGRAPH_ARGUMENT;
        }
        return null;
    }

    @Override
    public void prepare() {
        getSelectInPassageQuestions().forEach(question -> question.prepare());
        getMultipleAnswerQuestions().forEach(question -> question.prepare());
        getSingleAnswerQuestions().forEach(question -> question.prepare());
    }

    public enum Type {
        SHORT,
        MEDIUM,
        LONG,
        PARAGRAPH_ARGUMENT
    }
}
