package com.ztc.testcenter.gre.controller.question.qc;

import com.ztc.testcenter.gre.domain.question.QuantitativeComparisonQuestion;
import com.ztc.testcenter.gre.dto.QuantitativeComparisonQuestionDTO;
import com.ztc.testcenter.gre.service.QuestionService;
import com.ztc.testcenter.gre.specification.QuestionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
public class GetQuantitativeComparisonQuestionsController {

    final private QuestionService questionService;

    @Autowired
    public GetQuantitativeComparisonQuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/gre-service/question/quantitative-comparison")
    @PreAuthorize("hasAuthority('GRE_CONTENT')")
    public Page<QuantitativeComparisonQuestionDTO> handle(@ModelAttribute QuestionSpecification<QuantitativeComparisonQuestion> specification, Pageable pageable) {
        return questionService.findAllQuestions(specification, pageable, QuantitativeComparisonQuestion.class).map(QuantitativeComparisonQuestionDTO::valueOf);
    }

}
