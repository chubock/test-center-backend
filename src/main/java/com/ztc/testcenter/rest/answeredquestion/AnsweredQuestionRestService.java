package com.ztc.testcenter.rest.answeredquestion;

import com.ztc.testcenter.domain.test.AnsweredQuestion;
import com.ztc.testcenter.domain.user.User;
import com.ztc.testcenter.dto.question.QuestionDTO;
import com.ztc.testcenter.repository.test.AnsweredQuestionRepository;
import com.ztc.testcenter.security.ApplicationUserDetails;
import com.ztc.testcenter.service.QuestionService;
import com.ztc.testcenter.service.TeacherService;
import com.ztc.testcenter.specification.AnsweredQuestionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yubar on 5/29/17.
 */

@RestController
@RequestMapping("/answered-questions")
public class AnsweredQuestionRestService {

    private final QuestionService questionService;
    private final TeacherService teacherService;
    private final AnsweredQuestionRepository answeredQuestionRepository;
    private final GrantedAuthority allAnsweredQuestionAuthority = new SimpleGrantedAuthority("ANSWERED_QUESTION_REST_SERVICE__GET_ANSWERED_QUESTIONS_ALL");

    @Autowired
    public AnsweredQuestionRestService(QuestionService questionService, TeacherService teacherService, AnsweredQuestionRepository answeredQuestionRepository) {
        this.questionService = questionService;
        this.teacherService = teacherService;
        this.answeredQuestionRepository = answeredQuestionRepository;
    }

    private User getUser(Authentication authentication) {
        return ((ApplicationUserDetails) authentication.getPrincipal()).getUser();
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ANSWERED_QUESTION_REST_SERVICE__GET_ANSWERED_QUESTIONS', 'ANSWERED_QUESTION_REST_SERVICE__GET_ANSWERED_QUESTIONS_ALL')")
    public Page<QuestionDTO> getAnsweredQuestions(@ModelAttribute AnsweredQuestionSpecification specification, Authentication authentication, Pageable pageable) {
        User currentUser = getUser(authentication);
        if (!authentication.getAuthorities().contains(allAnsweredQuestionAuthority))
            specification.setUser(currentUser);
        Page<AnsweredQuestion> page = answeredQuestionRepository.findAll(specification, pageable);
        questionService.findQuestions(page.getContent());
        return page.map(QuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PATCH)
    @PreAuthorize("hasAuthority('ANSWERED_QUESTION_REST_SERVICE__SCORE_ANSWERED_QUESTIONS')")
    public void scoreAnsweredQuestion(@RequestBody ScoreAnsweredQuestionRequest questionDTO, Authentication authentication) {
        teacherService.scoreAnsweredQuestion(questionDTO.getId(), questionDTO.getScore(), questionDTO.getComment());
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
