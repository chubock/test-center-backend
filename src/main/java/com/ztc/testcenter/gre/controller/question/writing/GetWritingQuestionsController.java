package com.ztc.testcenter.gre.controller.question.writing;

import com.ztc.testcenter.gre.domain.question.WritingQuestion;
import com.ztc.testcenter.gre.dto.WritingQuestionDTO;
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
public class GetWritingQuestionsController {

    final private QuestionService questionService;

    @Autowired
    public GetWritingQuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/gre-service/question/writing")
    @PreAuthorize("hasAuthority('GRE_CONTENT')")
    public Page<WritingQuestionDTO> handle(@ModelAttribute QuestionSpecification<WritingQuestion> specification, Pageable pageable) {
        return questionService.findAllQuestions(specification, pageable, WritingQuestion.class).map(WritingQuestionDTO::valueOf);
    }

}
