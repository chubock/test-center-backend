package com.ztc.testcenter.gre.controller.question.numeric;

import com.ztc.testcenter.gre.domain.question.NumericQuestion;
import com.ztc.testcenter.gre.dto.NumericQuestionDTO;
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
public class GetNumericQuestionsController {

    final private QuestionService questionService;

    @Autowired
    public GetNumericQuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/gre-service/question/numeric")
    @PreAuthorize("hasAuthority('GRE_CONTENT')")
    public Page<NumericQuestionDTO> handle(@ModelAttribute QuestionSpecification<NumericQuestion> specification, Pageable pageable) {
        return questionService.findAllQuestions(specification, pageable, NumericQuestion.class).map(NumericQuestionDTO::valueOf);
    }

}
