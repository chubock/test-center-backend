package com.ztc.testcenter.question.gre.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Yubar on 1/27/2017.
 */

@Entity
@DiscriminatorValue("DATA_INTERPRETATION_SET")
public class DataInterpretationSetQuestion extends Question implements QuestionsContainer {

    private List<DataInterpretationNumericQuestion> numericQuestions = new ArrayList<>();
    private List<DataInterpretationMultipleAnswerQuestion> multipleAnswerQuestions = new ArrayList<>();
    private List<DataInterpretationSingleAnswerQuestion> singleAnswerQuestions = new ArrayList<>();

    @OneToMany(mappedBy = "parent", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    public List<DataInterpretationNumericQuestion> getNumericQuestions() {
        return numericQuestions;
    }

    public void setNumericQuestions(List<DataInterpretationNumericQuestion> numericQuestions) {
        this.numericQuestions = numericQuestions;
    }

    @OneToMany(mappedBy = "parent", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    public List<DataInterpretationMultipleAnswerQuestion> getMultipleAnswerQuestions() {
        return multipleAnswerQuestions;
    }

    public void setMultipleAnswerQuestions(List<DataInterpretationMultipleAnswerQuestion> multipleAnswerQuestions) {
        this.multipleAnswerQuestions = multipleAnswerQuestions;
    }

    @OneToMany(mappedBy = "parent", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    public List<DataInterpretationSingleAnswerQuestion> getSingleAnswerQuestions() {
        return singleAnswerQuestions;
    }

    public void setSingleAnswerQuestions(List<DataInterpretationSingleAnswerQuestion> singleAnswerQuestions) {
        this.singleAnswerQuestions = singleAnswerQuestions;
    }

    @Override
    public List<Question> innerQuestions() {
        List<Question> ret = new ArrayList<>(getNumericQuestions());
        ret.addAll(getMultipleAnswerQuestions());
        ret.addAll(getSingleAnswerQuestions());
        ret.sort(Comparator.comparingInt(x -> ((InnerQuestion) x).getNumber()));
        return ret;
    }

    @Override
    public QuestionType getQuestionType() {
        return QuestionType.GRE_DATA_INTERPRETATION_SET;
    }

    @Override
    public void prepare() {
        getNumericQuestions().forEach(question -> question.prepare());
        getMultipleAnswerQuestions().forEach(question -> question.prepare());
        getSingleAnswerQuestions().forEach(question -> question.prepare());
    }

    @Override
    public boolean isCorrect(String answers) {
        throw new IllegalStateException("Question Groups doesn't support isCorrect behaviour");
    }
}
