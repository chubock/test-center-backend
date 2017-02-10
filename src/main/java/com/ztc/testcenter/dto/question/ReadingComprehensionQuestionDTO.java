package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 2/9/2017.
 */
public class ReadingComprehensionQuestionDTO extends QuestionDTO {

    List<ReadingComprehensionSingleAnswerQuestionDTO> singleAnswerQuestions = new ArrayList<>();
    List<ReadingComprehensionMultipleAnswerQuestionDTO> multipleAnswerQuestions = new ArrayList<>();
    List<SelectInPassageQuestionDTO> selectInPassageQuestions = new ArrayList<>();

    public List<ReadingComprehensionSingleAnswerQuestionDTO> getSingleAnswerQuestions() {
        return singleAnswerQuestions;
    }

    public void setSingleAnswerQuestions(List<ReadingComprehensionSingleAnswerQuestionDTO> singleAnswerQuestions) {
        this.singleAnswerQuestions = singleAnswerQuestions;
    }

    public List<ReadingComprehensionMultipleAnswerQuestionDTO> getMultipleAnswerQuestions() {
        return multipleAnswerQuestions;
    }

    public void setMultipleAnswerQuestions(List<ReadingComprehensionMultipleAnswerQuestionDTO> multipleAnswerQuestions) {
        this.multipleAnswerQuestions = multipleAnswerQuestions;
    }

    public List<SelectInPassageQuestionDTO> getSelectInPassageQuestions() {
        return selectInPassageQuestions;
    }

    public void setSelectInPassageQuestions(List<SelectInPassageQuestionDTO> selectInPassageQuestions) {
        this.selectInPassageQuestions = selectInPassageQuestions;
    }

    void convert(ReadingComprehensionQuestion question) {
        super.convert(question);
        getSingleAnswerQuestions().forEach(numericQuestion -> question.getSingleAnswerQuestions().add(numericQuestion.convert()));
        getMultipleAnswerQuestions().forEach(multipleAnswerQuestion -> question.getMultipleAnswerQuestions().add(multipleAnswerQuestion.convert()));
        getSelectInPassageQuestions().forEach(singleAnswerQuestion -> question.getSelectInPassageQuestions().add(singleAnswerQuestion.convert()));
    }

    void copy(ReadingComprehensionQuestion question) {
        super.copy(question);
        question.getSingleAnswerQuestions().forEach(singleAnswerQuestion -> getSingleAnswerQuestions().add(ReadingComprehensionSingleAnswerQuestionDTO.valueOf(singleAnswerQuestion)));
        question.getMultipleAnswerQuestions().forEach(multipleAnswerQuestion -> getMultipleAnswerQuestions().add(ReadingComprehensionMultipleAnswerQuestionDTO.valueOf(multipleAnswerQuestion)));
        question.getSelectInPassageQuestions().forEach(selectInPassageQuestion -> getSelectInPassageQuestions().add(SelectInPassageQuestionDTO.valueOf(selectInPassageQuestion)));
    }

    @Override
    public ReadingComprehensionQuestion convert() {
        ReadingComprehensionQuestion question = new ReadingComprehensionQuestion();
        convert(question);
        return question;
    }

    public static ReadingComprehensionQuestionDTO valueOf(ReadingComprehensionQuestion question) {
        if (question == null)
            return null;
        ReadingComprehensionQuestionDTO questionDTO = new ReadingComprehensionQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }
}
