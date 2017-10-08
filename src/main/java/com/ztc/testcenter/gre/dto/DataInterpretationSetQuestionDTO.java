package com.ztc.testcenter.gre.dto;

import com.ztc.testcenter.gre.domain.question.DataInterpretationMultipleAnswerQuestion;
import com.ztc.testcenter.gre.domain.question.DataInterpretationSetQuestion;
import com.ztc.testcenter.gre.domain.question.DataInterpretationSingleAnswerQuestion;
import com.ztc.testcenter.gre.domain.question.DataInterpretationNumericQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/27/2017.
 */

public class DataInterpretationSetQuestionDTO extends QuestionDTO<DataInterpretationSetQuestion> {

    private List<DataInterpretationNumericQuestionDTO> numericQuestions = new ArrayList<>();
    private List<DataInterpretationMultipleAnswerQuestionDTO> multipleAnswerQuestions = new ArrayList<>();
    private List<DataInterpretationSingleAnswerQuestionDTO> singleAnswerQuestions = new ArrayList<>();

    public List<DataInterpretationNumericQuestionDTO> getNumericQuestions() {
        return numericQuestions;
    }

    public void setNumericQuestions(List<DataInterpretationNumericQuestionDTO> numericQuestions) {
        this.numericQuestions = numericQuestions;
    }

    public List<DataInterpretationMultipleAnswerQuestionDTO> getMultipleAnswerQuestions() {
        return multipleAnswerQuestions;
    }

    public void setMultipleAnswerQuestions(List<DataInterpretationMultipleAnswerQuestionDTO> multipleAnswerQuestions) {
        this.multipleAnswerQuestions = multipleAnswerQuestions;
    }

    public List<DataInterpretationSingleAnswerQuestionDTO> getSingleAnswerQuestions() {
        return singleAnswerQuestions;
    }

    public void setSingleAnswerQuestions(List<DataInterpretationSingleAnswerQuestionDTO> singleAnswerQuestions) {
        this.singleAnswerQuestions = singleAnswerQuestions;
    }

    @Override
    public DataInterpretationSetQuestion convert(DataInterpretationSetQuestion question) {
        super.convert(question);
        getNumericQuestions().forEach(numericQuestionDTO -> {
            DataInterpretationNumericQuestion numericQuestion = numericQuestionDTO.convert(new DataInterpretationNumericQuestion(question, numericQuestionDTO.getNumber()));
            numericQuestion.setParent(question);
            question.getNumericQuestions().add(numericQuestion);
        });
        getMultipleAnswerQuestions().forEach(multipleAnswerQuestionDTO -> {
            DataInterpretationMultipleAnswerQuestion multipleAnswerQuestion = multipleAnswerQuestionDTO.convert(new DataInterpretationMultipleAnswerQuestion(question, multipleAnswerQuestionDTO.getNumber()));
            multipleAnswerQuestion.setParent(question);
            question.getMultipleAnswerQuestions().add(multipleAnswerQuestion);
        });
        getSingleAnswerQuestions().forEach(singleAnswerQuestionDTO -> {
            DataInterpretationSingleAnswerQuestion singleAnswerQuestion = singleAnswerQuestionDTO.convert(new DataInterpretationSingleAnswerQuestion(question, singleAnswerQuestionDTO.getNumber()));
            singleAnswerQuestion.setParent(question);
            question.getSingleAnswerQuestions().add(singleAnswerQuestion);
        });
        return question;
    }

    void copy(DataInterpretationSetQuestion question) {
        copy(question, false);
    }

    void copy(DataInterpretationSetQuestion question, boolean lazy) {
        super.copy(question);
        if (!lazy) {
            question.getNumericQuestions().forEach(numericQuestion -> getNumericQuestions().add(DataInterpretationNumericQuestionDTO.valueOf(numericQuestion)));
            question.getMultipleAnswerQuestions().forEach(multipleAnswerQuestion -> getMultipleAnswerQuestions().add(DataInterpretationMultipleAnswerQuestionDTO.valueOf(multipleAnswerQuestion)));
            question.getSingleAnswerQuestions().forEach(singleAnswerQuestion -> getSingleAnswerQuestions().add(DataInterpretationSingleAnswerQuestionDTO.valueOf(singleAnswerQuestion)));
        }
    }

    public static DataInterpretationSetQuestionDTO valueOf(DataInterpretationSetQuestion question, boolean lazy) {
        if (question == null)
            return null;
        DataInterpretationSetQuestionDTO questionDTO = new DataInterpretationSetQuestionDTO();
        questionDTO.copy(question, lazy);
        return questionDTO;
    }

    public static DataInterpretationSetQuestionDTO valueOf(DataInterpretationSetQuestion question) {
        return valueOf(question, false);
    }

    @Override
    public void setUserAnswer(String answer) {
        //Operation Not Supported For Question Groups
    }
}
