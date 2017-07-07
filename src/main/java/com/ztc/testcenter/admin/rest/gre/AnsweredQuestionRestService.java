package com.ztc.testcenter.admin.rest.gre;

import com.ztc.testcenter.question.gre.dto.QuestionDTO;
import com.ztc.testcenter.test.gre.service.AnsweredQuestionsService;
import com.ztc.testcenter.test.gre.specification.AnsweredQuestionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yubar on 5/29/17.
 */

@RestController("AdminAnsweredQuestionRestService")
@RequestMapping("/admin/answered-questions")
public class AnsweredQuestionRestService {

    private final AnsweredQuestionsService answeredQuestionsService;

    @Autowired
    public AnsweredQuestionRestService(AnsweredQuestionsService answeredQuestionsService) {
        this.answeredQuestionsService = answeredQuestionsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ANSWERED_QUESTION_REST_SERVICE__GET_ANSWERED_QUESTIONS_ALL')")
    public Page<QuestionDTO> getAnsweredQuestions(@ModelAttribute AnsweredQuestionSpecification specification, Pageable pageable) {
        return answeredQuestionsService.findAnsweredQuestions(specification, pageable).map(QuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    @PreAuthorize("hasAuthority('ANSWERED_QUESTION_REST_SERVICE__SCORE_ANSWERED_QUESTIONS')")
    public void scoreAnsweredQuestion(@RequestBody ScoreAnsweredQuestionRequest questionDTO) {
        answeredQuestionsService.scoreAnsweredQuestion(questionDTO.getId(), questionDTO.getScore(), questionDTO.getComment());
    }

    public static class ScoreAnsweredQuestionRequest {
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
