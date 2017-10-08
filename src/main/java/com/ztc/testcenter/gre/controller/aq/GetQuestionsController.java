package com.ztc.testcenter.gre.controller.aq;

import com.ztc.testcenter.gre.dto.QuestionDTO;
import com.ztc.testcenter.gre.service.AnsweredQuestionService;
import com.ztc.testcenter.gre.specification.AnsweredQuestionSpecification;
import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yubar on 9/20/17.
 */

@RestController
public class GetQuestionsController {

    private final AnsweredQuestionService answeredQuestionService;

    @Autowired
    public GetQuestionsController(AnsweredQuestionService answeredQuestionService) {
        this.answeredQuestionService = answeredQuestionService;
    }

    @GetMapping("/gre-service/answered-questions")
    @PreAuthorize("hasAuthority('STUDENT')")
    public Page<QuestionDTO> getAnsweredQuestions(@ModelAttribute AnsweredQuestionSpecification specification, Authentication authentication, Pageable pageable) {
        specification.setUsername(SecurityUtil.getUsername(authentication));
        return answeredQuestionService.findAnsweredQuestions(specification, pageable).map(QuestionDTO::valueOf);
    }
}
