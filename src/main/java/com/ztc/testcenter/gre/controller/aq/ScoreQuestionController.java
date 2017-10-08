package com.ztc.testcenter.gre.controller.aq;

import com.ztc.testcenter.gre.service.AnsweredQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yubar on 9/20/17.
 */

@RestController
public class ScoreQuestionController {

    private final AnsweredQuestionService answeredQuestionService;

    @Autowired
    public ScoreQuestionController(AnsweredQuestionService answeredQuestionService) {
        this.answeredQuestionService = answeredQuestionService;
    }

    @PutMapping("/gre-service/answered-questions/{id}/score")
    @PreAuthorize("hasAuthority('STUDENT')")
    public void scoreAnsweredQuestion(@RequestBody Request request) {
        answeredQuestionService.scoreQuestion(request.getId(), request.getScore(), request.getComment());
    }

    public static class Request {
        private Long id;
        private Double score;
        private String comment;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }
}
