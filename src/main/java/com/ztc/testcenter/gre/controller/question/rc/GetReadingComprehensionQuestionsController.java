package com.ztc.testcenter.gre.controller.question.rc;

import com.ztc.testcenter.gre.domain.question.ReadingComprehensionQuestion;
import com.ztc.testcenter.gre.dto.ReadingComprehensionQuestionDTO;
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
public class GetReadingComprehensionQuestionsController {

    final private QuestionService questionService;

    @Autowired
    public GetReadingComprehensionQuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/gre-service/question/reading-comprehension")
    @PreAuthorize("hasAuthority('GRE_CONTENT')")
    public Page<ReadingComprehensionQuestionDTO> handle(@ModelAttribute QuestionSpecification<ReadingComprehensionQuestion> specification, Pageable pageable) {
        return questionService.findAllQuestions(specification, pageable, ReadingComprehensionQuestion.class).map(ReadingComprehensionQuestionDTO::valueOf);
    }

}
