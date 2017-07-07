package com.ztc.testcenter.student.rest.gre;

import com.ztc.testcenter.config.security.ApplicationUserDetails;
import com.ztc.testcenter.question.gre.dto.QuestionDTO;
import com.ztc.testcenter.test.gre.service.AnsweredQuestionsService;
import com.ztc.testcenter.test.gre.specification.AnsweredQuestionSpecification;
import com.ztc.testcenter.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yubar on 5/29/17.
 */

@RestController("StudentAnsweredQuestionRestService")
@RequestMapping("/student/answered-questions")
public class AnsweredQuestionRestService {

    private final AnsweredQuestionsService answeredQuestionsService;

    @Autowired
    public AnsweredQuestionRestService(AnsweredQuestionsService answeredQuestionsService) {
        this.answeredQuestionsService = answeredQuestionsService;
    }

    private User getUser(Authentication authentication) {
        return ((ApplicationUserDetails) authentication.getPrincipal()).getUser();
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ANSWERED_QUESTION_REST_SERVICE__GET_ANSWERED_QUESTIONS')")
    public Page<QuestionDTO> getAnsweredQuestions(@ModelAttribute AnsweredQuestionSpecification specification, Authentication authentication, Pageable pageable) {
        User currentUser = getUser(authentication);
        specification.setUser(currentUser);
        return answeredQuestionsService.findAnsweredQuestions(specification, pageable).map(QuestionDTO::valueOf);
    }

}
