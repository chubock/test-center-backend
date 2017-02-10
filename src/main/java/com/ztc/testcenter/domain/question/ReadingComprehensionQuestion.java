package com.ztc.testcenter.domain.question;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 2/9/2017.
 */

@Entity
@DiscriminatorValue("READING_COMPREHENSION")
public class ReadingComprehensionQuestion extends Question {

    List<ReadingComprehensionSingleAnswerQuestion> singleAnswerQuestions = new ArrayList<>();
    List<ReadingComprehensionMultipleAnswerQuestion> multipleAnswerQuestions = new ArrayList<>();
    List<SelectInPassageQuestion> selectInPassageQuestions = new ArrayList<>();

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
    public void prepare() {
        getSelectInPassageQuestions().forEach(question -> question.prepare());
        getMultipleAnswerQuestions().forEach(question -> question.prepare());
        getSingleAnswerQuestions().forEach(question -> question.prepare());
    }
}
