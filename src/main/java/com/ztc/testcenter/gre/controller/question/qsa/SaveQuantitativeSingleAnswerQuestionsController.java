package com.ztc.testcenter.gre.controller.question.qsa;

import com.ztc.testcenter.file.service.FileService;
import com.ztc.testcenter.gre.domain.question.QuantitativeSingleAnswerQuestion;
import com.ztc.testcenter.gre.dto.QuantitativeSingleAnswerQuestionDTO;
import com.ztc.testcenter.gre.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
public class SaveQuantitativeSingleAnswerQuestionsController {

    final private QuestionService questionService;
    final private FileService fileService;

    @Autowired
    public SaveQuantitativeSingleAnswerQuestionsController(QuestionService questionService, FileService fileService) {
        this.questionService = questionService;
        this.fileService = fileService;
    }

    @PutMapping("/gre-service/question/quantitative-single-answer")
    @PreAuthorize("hasAuthority('GRE_CONTENT')")
    public QuantitativeSingleAnswerQuestionDTO handle(@RequestBody QuantitativeSingleAnswerQuestionDTO questionDTO) {
        QuantitativeSingleAnswerQuestion question = questionDTO.convert(new QuantitativeSingleAnswerQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileService.get(questionDTO.getImage()));
        question = (QuantitativeSingleAnswerQuestion) questionService.save(question);
        questionDTO = QuantitativeSingleAnswerQuestionDTO.valueOf(question);
        return questionDTO;
    }

}
