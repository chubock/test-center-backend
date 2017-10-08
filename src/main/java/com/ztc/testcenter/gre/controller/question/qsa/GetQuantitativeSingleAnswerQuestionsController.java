package com.ztc.testcenter.gre.controller.question.qsa;

import com.ztc.testcenter.gre.domain.question.QuantitativeSingleAnswerQuestion;
import com.ztc.testcenter.gre.dto.QuantitativeSingleAnswerQuestionDTO;
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
public class GetQuantitativeSingleAnswerQuestionsController {

    final private QuestionService questionService;

    @Autowired
    public GetQuantitativeSingleAnswerQuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/gre-service/question/quantitative-single-answer")
    @PreAuthorize("hasAuthority('GRE_CONTENT')")
    public Page<QuantitativeSingleAnswerQuestionDTO> handle(@ModelAttribute QuestionSpecification<QuantitativeSingleAnswerQuestion> specification, Pageable pageable) {
        return questionService.findAllQuestions(specification, pageable, QuantitativeSingleAnswerQuestion.class).map(QuantitativeSingleAnswerQuestionDTO::valueOf);
    }

}
