package com.ztc.testcenter.domain.question;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 2/9/2017.
 */

@Entity
public class ReadingComprehensionQuestion extends Question {

    private QuestionTemplate template;
    private List<ReadingComprehensionSingleAnswerQuestion> singleAnswerQuestions = new ArrayList<>();
    private List<ReadingComprehensionMultipleAnswerQuestion> multipleAnswerQuestions = new ArrayList<>();
    private List<SelectInPassageQuestion> selectInPassageQuestions = new ArrayList<>();

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public QuestionTemplate getTemplate() {
        return template;
    }

    public void setTemplate(QuestionTemplate template) {
        this.template = template;
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
    QuestionType getQuestionType() {
        return QuestionType.GRE_READING_COMPREHENSION;
    }

    @Override
    public void prepare() {
        getSelectInPassageQuestions().forEach(question -> question.prepare());
        getMultipleAnswerQuestions().forEach(question -> question.prepare());
        getSingleAnswerQuestions().forEach(question -> question.prepare());
    }
}
