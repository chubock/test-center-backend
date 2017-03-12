package com.ztc.testcenter.dto.question;

import com.ztc.testcenter.domain.question.DataInterpretationSetQuestion;
import com.ztc.testcenter.domain.question.Question;
import com.ztc.testcenter.domain.test.AnsweredQuestion;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 1/27/2017.
 */

public class DataInterpretationSetQuestionDTO extends QuestionDTO {

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

    void convert(DataInterpretationSetQuestion question) {
        super.convert(question);
        getNumericQuestions().forEach(numericQuestion -> question.getNumericQuestions().add(numericQuestion.convert()));
        getMultipleAnswerQuestions().forEach(multipleAnswerQuestion -> question.getMultipleAnswerQuestions().add(multipleAnswerQuestion.convert()));
        getSingleAnswerQuestions().forEach(singleAnswerQuestion -> question.getSingleAnswerQuestions().add(singleAnswerQuestion.convert()));
    }

    void copy(DataInterpretationSetQuestion question) {
        super.copy(question);
        question.getNumericQuestions().forEach(numericQuestion -> getNumericQuestions().add(DataInterpretationNumericQuestionDTO.valueOf(numericQuestion)));
        question.getMultipleAnswerQuestions().forEach(multipleAnswerQuestion -> getMultipleAnswerQuestions().add(DataInterpretationMultipleAnswerQuestionDTO.valueOf(multipleAnswerQuestion)));
        question.getSingleAnswerQuestions().forEach(singleAnswerQuestion -> getSingleAnswerQuestions().add(DataInterpretationSingleAnswerQuestionDTO.valueOf(singleAnswerQuestion)));
    }

    @Override
    public DataInterpretationSetQuestion convert() {
        DataInterpretationSetQuestion question = new DataInterpretationSetQuestion();
        convert(question);
        return question;
    }

    public static DataInterpretationSetQuestionDTO valueOf(DataInterpretationSetQuestion question) {
        if (question == null)
            return null;
        DataInterpretationSetQuestionDTO questionDTO = new DataInterpretationSetQuestionDTO();
        questionDTO.copy(question);
        return questionDTO;
    }

    public static DataInterpretationSetQuestionDTO valueOf(AnsweredQuestion answeredQuestion) {
        if (answeredQuestion == null)
            return null;
        DataInterpretationSetQuestionDTO questionDTO = valueOf((DataInterpretationSetQuestion) answeredQuestion.getQuestion());
        return questionDTO;
    }

    @Override
    public void setAnswer(String answer) {
        //Operation Not Supported For Question Groups
    }
}
