package com.ztc.testcenter.gre.controller.result;

import com.ztc.testcenter.config.security.ApplicationUserDetails;
import com.ztc.testcenter.gre.domain.result.AnsweredQuestionResult;
import com.ztc.testcenter.gre.service.QuestionResultService;
import com.ztc.testcenter.gre.specification.TestResultSpecification;
import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yubar on 9/20/17.
 */

@RestController
public class GetTestQuestionsResultController {

    private final QuestionResultService questionResultService;

    @Autowired
    public GetTestQuestionsResultController(QuestionResultService questionResultService) {
        this.questionResultService = questionResultService;
    }

    @GetMapping("/gre-service/tests/{id}/answered-questions/result")
    @PreAuthorize("hasAuthority('STUDENT')")
    public List<AnsweredQuestionResult> getTestAnsweredQuestionResults(@PathVariable Long id, @ModelAttribute TestResultSpecification specification, Authentication authentication) {
        specification.setUsername(SecurityUtil.getUsername(authentication));
        specification.setTestId(id);
        return questionResultService.getResults(specification);
    }
}
