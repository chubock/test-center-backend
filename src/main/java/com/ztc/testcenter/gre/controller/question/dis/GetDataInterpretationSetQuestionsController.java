package com.ztc.testcenter.gre.controller.question.dis;

import com.ztc.testcenter.gre.domain.question.DataInterpretationSetQuestion;
import com.ztc.testcenter.gre.dto.DataInterpretationSetQuestionDTO;
import com.ztc.testcenter.gre.service.QuestionService;
import com.ztc.testcenter.gre.specification.QuestionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
public class GetDataInterpretationSetQuestionsController {

    final private QuestionService questionService;

    @Autowired
    public GetDataInterpretationSetQuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/gre-service/question/data-interpretation-set")
    @PreAuthorize("hasAuthority('GRE_CONTENT')")
    public Page<DataInterpretationSetQuestionDTO> handle(@ModelAttribute QuestionSpecification<DataInterpretationSetQuestion> specification, Pageable pageable) {
        return questionService.findAllQuestions(specification, pageable, DataInterpretationSetQuestion.class).map(DataInterpretationSetQuestionDTO::valueOf);
    }

}
