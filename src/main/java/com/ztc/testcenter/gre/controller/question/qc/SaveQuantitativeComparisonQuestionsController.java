package com.ztc.testcenter.gre.controller.question.qc;

import com.ztc.testcenter.file.service.FileService;
import com.ztc.testcenter.gre.domain.question.QuantitativeComparisonQuestion;
import com.ztc.testcenter.gre.dto.QuantitativeComparisonQuestionDTO;
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
public class SaveQuantitativeComparisonQuestionsController {

    final private QuestionService questionService;
    final private FileService fileService;

    @Autowired
    public SaveQuantitativeComparisonQuestionsController(QuestionService questionService, FileService fileService) {
        this.questionService = questionService;
        this.fileService = fileService;
    }

    @PutMapping("/gre-service/question/quantitative-comparison")
    @PreAuthorize("hasAuthority('GRE_CONTENT')")
    public QuantitativeComparisonQuestionDTO handle(@RequestBody QuantitativeComparisonQuestionDTO questionDTO) {
        QuantitativeComparisonQuestion question = questionDTO.convert(new QuantitativeComparisonQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileService.get(questionDTO.getImage()));
        question = (QuantitativeComparisonQuestion) questionService.save(question);
        questionDTO = QuantitativeComparisonQuestionDTO.valueOf(question);
        return questionDTO;
    }

}
