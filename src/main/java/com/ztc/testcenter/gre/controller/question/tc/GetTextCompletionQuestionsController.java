package com.ztc.testcenter.gre.controller.question.tc;

import com.ztc.testcenter.gre.domain.question.TextCompletionQuestion;
import com.ztc.testcenter.gre.dto.TextCompletionQuestionDTO;
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
public class GetTextCompletionQuestionsController {

    final private QuestionService questionService;

    @Autowired
    public GetTextCompletionQuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/gre-service/question/text-completion")
    @PreAuthorize("hasAuthority('GRE_CONTENT')")
    public Page<TextCompletionQuestionDTO> handle(@ModelAttribute QuestionSpecification<TextCompletionQuestion> specification, Pageable pageable) {
        return questionService.findAllQuestions(specification, pageable, TextCompletionQuestion.class).map(TextCompletionQuestionDTO::valueOf);
    }

}
