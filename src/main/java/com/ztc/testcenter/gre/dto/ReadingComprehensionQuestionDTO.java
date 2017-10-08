package com.ztc.testcenter.gre.dto;

import com.ztc.testcenter.gre.domain.question.ReadingComprehensionQuestion;
import com.ztc.testcenter.gre.domain.question.ReadingComprehensionSingleAnswerQuestion;
import com.ztc.testcenter.gre.domain.question.ReadingComprehensionMultipleAnswerQuestion;
import com.ztc.testcenter.gre.domain.question.SelectInPassageQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 2/9/2017.
 */
public class ReadingComprehensionQuestionDTO extends QuestionDTO<ReadingComprehensionQuestion> {

    private ReadingComprehensionQuestion.Type type = ReadingComprehensionQuestion.Type.MEDIUM;
    List<ReadingComprehensionSingleAnswerQuestionDTO> singleAnswerQuestions = new ArrayList<>();
    List<ReadingComprehensionMultipleAnswerQuestionDTO> multipleAnswerQuestions = new ArrayList<>();
    List<SelectInPassageQuestionDTO> selectInPassageQuestions = new ArrayList<>();

    public ReadingComprehensionQuestion.Type getType() {
        return type;
    }

    public void setType(ReadingComprehensionQuestion.Type type) {
        this.type = type;
    }

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

    @Override
    public ReadingComprehensionQuestion convert(ReadingComprehensionQuestion question) {
        super.convert(question);
        question.setType(getType());
        getSingleAnswerQuestions().forEach(singleAnswerQuestionDTO -> {
            ReadingComprehensionSingleAnswerQuestion singleAnswerQuestion = singleAnswerQuestionDTO.convert(new ReadingComprehensionSingleAnswerQuestion(question, singleAnswerQuestionDTO.getNumber()));
            singleAnswerQuestion.setParent(question);
            question.getSingleAnswerQuestions().add(singleAnswerQuestion);
        });
        getMultipleAnswerQuestions().forEach(multipleAnswerQuestionDTO -> {
            ReadingComprehensionMultipleAnswerQuestion multipleAnswerQuestion = multipleAnswerQuestionDTO.convert(new ReadingComprehensionMultipleAnswerQuestion(question, multipleAnswerQuestionDTO.getNumber()));
            multipleAnswerQuestion.setParent(question);
            question.getMultipleAnswerQuestions().add(multipleAnswerQuestion);
        });
        getSelectInPassageQuestions().forEach(selectInPassageQuestionDTO -> {
            SelectInPassageQuestion selectInPassageQuestion = selectInPassageQuestionDTO.convert(new SelectInPassageQuestion(question, selectInPassageQuestionDTO.getNumber()));
            selectInPassageQuestion.setParent(question);
            question.getSelectInPassageQuestions().add(selectInPassageQuestion);
        });
        return question;
    }

    void copy(ReadingComprehensionQuestion question) {
        copy(question, false);
    }

    void copy(ReadingComprehensionQuestion question, boolean lazy) {
        super.copy(question);
        setType(question.getType());
        if (! lazy) {
            question.getSingleAnswerQuestions().forEach(singleAnswerQuestion -> getSingleAnswerQuestions().add(ReadingComprehensionSingleAnswerQuestionDTO.valueOf(singleAnswerQuestion)));
            question.getMultipleAnswerQuestions().forEach(multipleAnswerQuestion -> getMultipleAnswerQuestions().add(ReadingComprehensionMultipleAnswerQuestionDTO.valueOf(multipleAnswerQuestion)));
            question.getSelectInPassageQuestions().forEach(selectInPassageQuestion -> getSelectInPassageQuestions().add(SelectInPassageQuestionDTO.valueOf(selectInPassageQuestion)));
        }
    }

    public static ReadingComprehensionQuestionDTO valueOf(ReadingComprehensionQuestion question, boolean lazy) {
        if (question == null)
            return null;
        ReadingComprehensionQuestionDTO questionDTO = new ReadingComprehensionQuestionDTO();
        questionDTO.copy(question, lazy);
        return questionDTO;
    }

    public static ReadingComprehensionQuestionDTO valueOf(ReadingComprehensionQuestion question) {
        return valueOf(question, false);
    }

    @Override
    public void setUserAnswer(String answer) {
        //Operation Not Supported For Question Groups
    }
}
