package com.ztc.testcenter.gre.controller.aq;

import com.ztc.testcenter.gre.service.AnsweredQuestionService;
import com.ztc.testcenter.gre.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yubar on 9/20/17.
 */

@RestController
public class UnMarkQuestionController {

    private final AnsweredQuestionService answeredQuestionService;

    @Autowired
    public UnMarkQuestionController(AnsweredQuestionService answeredQuestionService) {
        this.answeredQuestionService = answeredQuestionService;
    }

    @PutMapping("/gre-service/answered-questions/{id}/un-mark")
    @PreAuthorize("hasAuthority('STUDENT')")
    public void handle(@PathVariable Long id) {
        answeredQuestionService.markQuestion(id);
    }


}
