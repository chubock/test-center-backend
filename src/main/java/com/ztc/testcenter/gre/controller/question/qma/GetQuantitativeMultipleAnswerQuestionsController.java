package com.ztc.testcenter.gre.controller.question.qma;

import com.ztc.testcenter.gre.domain.question.QuantitativeMultipleAnswerQuestion;
import com.ztc.testcenter.gre.dto.QuantitativeMultipleAnswerQuestionDTO;
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
public class GetQuantitativeMultipleAnswerQuestionsController {

    final private QuestionService questionService;

    @Autowired
    public GetQuantitativeMultipleAnswerQuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/gre-service/question/quantitative-multiple-answer")
    @PreAuthorize("hasAuthority('GRE_CONTENT')")
    public Page<QuantitativeMultipleAnswerQuestionDTO> handle(@ModelAttribute QuestionSpecification<QuantitativeMultipleAnswerQuestion> specification, Pageable pageable) {
        return questionService.findAllQuestions(specification, pageable, QuantitativeMultipleAnswerQuestion.class).map(QuantitativeMultipleAnswerQuestionDTO::valueOf);
    }

}
