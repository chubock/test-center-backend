package com.ztc.testcenter.gre.controller.question.se;

import com.ztc.testcenter.gre.domain.question.SentenceEquivalenceQuestion;
import com.ztc.testcenter.gre.dto.SentenceEquivalenceQuestionDTO;
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
public class GetSentenceEquivalenceQuestionsController {

    final private QuestionService questionService;

    @Autowired
    public GetSentenceEquivalenceQuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/gre-service/question/sentence-equivalence")
    @PreAuthorize("hasAuthority('GRE_CONTENT')")
    public Page<SentenceEquivalenceQuestionDTO> handle(@ModelAttribute QuestionSpecification<SentenceEquivalenceQuestion> specification, Pageable pageable) {
        return questionService.findAllQuestions(specification, pageable, SentenceEquivalenceQuestion.class).map(SentenceEquivalenceQuestionDTO::valueOf);
    }

}
