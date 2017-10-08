package com.ztc.testcenter.gre.controller.question.qma;

import com.ztc.testcenter.file.service.FileService;
import com.ztc.testcenter.gre.domain.question.QuantitativeMultipleAnswerQuestion;
import com.ztc.testcenter.gre.dto.QuantitativeMultipleAnswerQuestionDTO;
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
public class SaveQuantitativeMultipleAnswerQuestionsController {

    final private QuestionService questionService;
    final private FileService fileService;

    @Autowired
    public SaveQuantitativeMultipleAnswerQuestionsController(QuestionService questionService, FileService fileService) {
        this.questionService = questionService;
        this.fileService = fileService;
    }

    @PutMapping("/gre-service/question/quantitative-multiple-answer")
    @PreAuthorize("hasAuthority('GRE_CONTENT')")
    public QuantitativeMultipleAnswerQuestionDTO handle(@RequestBody QuantitativeMultipleAnswerQuestionDTO questionDTO) {
        QuantitativeMultipleAnswerQuestion question = questionDTO.convert(new QuantitativeMultipleAnswerQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileService.get(questionDTO.getImage()));
        question = (QuantitativeMultipleAnswerQuestion) questionService.save(question);
        questionDTO = QuantitativeMultipleAnswerQuestionDTO.valueOf(question);
        return questionDTO;
    }

}
