package com.ztc.testcenter.gre.controller.aq;

import com.ztc.testcenter.gre.service.AnsweredQuestionService;
import com.ztc.testcenter.gre.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yubar on 9/20/17.
 */

@RestController
public class AnswerQuestionController {

    private final AnsweredQuestionService answeredQuestionService;

    @Autowired
    public AnswerQuestionController(AnsweredQuestionService answeredQuestionService) {
        this.answeredQuestionService = answeredQuestionService;
    }

    @PutMapping("/gre-service/answered-questions/{id}/answer")
    @PreAuthorize("hasAuthority('STUDENT')")
    public void handle(@PathVariable("id") Long answeredQuestionId, @RequestBody(required = false) String answer) {
        answeredQuestionService.answerQuestion(answeredQuestionId, answer);
    }
}
