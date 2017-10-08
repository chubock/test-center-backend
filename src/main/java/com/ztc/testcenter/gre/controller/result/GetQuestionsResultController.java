package com.ztc.testcenter.gre.controller.result;

import com.ztc.testcenter.gre.domain.result.AnsweredQuestionResult;
import com.ztc.testcenter.gre.service.QuestionResultService;
import com.ztc.testcenter.gre.specification.TestResultSpecification;
import com.ztc.testcenter.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by yubar on 9/20/17.
 */

@RestController
public class GetQuestionsResultController {

    private final QuestionResultService questionResultService;

    @Autowired
    public GetQuestionsResultController(QuestionResultService questionResultService) {
        this.questionResultService = questionResultService;
    }

    @GetMapping("/gre-service/answered-questions/result")
    @PreAuthorize("hasAuthority('STUDENT')")
    public List<AnsweredQuestionResult> getAnsweredQuestionResults(@ModelAttribute TestResultSpecification specification, Authentication authentication) {
        specification.setUsername(SecurityUtil.getUsername(authentication));
        return questionResultService.getResults(specification);
    }
}
