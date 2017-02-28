package com.ztc.testcenter.domain.question;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/27/2017.
 */

@Entity
@DiscriminatorValue("DATA_INTERPRETATION_SET")
public class DataInterpretationSetQuestion extends Question {

    private QuestionTemplate template;
    private List<DataInterpretationNumericQuestion> numericQuestions = new ArrayList<>();
    private List<DataInterpretationMultipleAnswerQuestion> multipleAnswerQuestions = new ArrayList<>();
    private List<DataInterpretationSingleAnswerQuestion> singleAnswerQuestions = new ArrayList<>();

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
    public List<DataInterpretationNumericQuestion> getNumericQuestions() {
        return numericQuestions;
    }

    public void setNumericQuestions(List<DataInterpretationNumericQuestion> numericQuestions) {
        this.numericQuestions = numericQuestions;
    }

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "question")
    public List<DataInterpretationMultipleAnswerQuestion> getMultipleAnswerQuestions() {
        return multipleAnswerQuestions;
    }

    public void setMultipleAnswerQuestions(List<DataInterpretationMultipleAnswerQuestion> multipleAnswerQuestions) {
        this.multipleAnswerQuestions = multipleAnswerQuestions;
    }

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "question")
    public List<DataInterpretationSingleAnswerQuestion> getSingleAnswerQuestions() {
        return singleAnswerQuestions;
    }

    public void setSingleAnswerQuestions(List<DataInterpretationSingleAnswerQuestion> singleAnswerQuestions) {
        this.singleAnswerQuestions = singleAnswerQuestions;
    }

    @Override
    QuestionType getQuestionType() {
        return QuestionType.GRE_DATA_INTERPRETATION_SET;
    }

    @Override
    public void prepare() {
        getNumericQuestions().forEach(question -> question.prepare());
        getMultipleAnswerQuestions().forEach(question -> question.prepare());
        getSingleAnswerQuestions().forEach(question -> question.prepare());
    }
}
