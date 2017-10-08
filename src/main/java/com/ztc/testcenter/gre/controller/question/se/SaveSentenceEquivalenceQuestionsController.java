package com.ztc.testcenter.gre.controller.question.se;

import com.ztc.testcenter.file.service.FileService;
import com.ztc.testcenter.gre.domain.question.SentenceEquivalenceQuestion;
import com.ztc.testcenter.gre.dto.SentenceEquivalenceQuestionDTO;
import com.ztc.testcenter.gre.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
public class SaveSentenceEquivalenceQuestionsController {

    final private QuestionService questionService;
    final private FileService fileService;

    @Autowired
    public SaveSentenceEquivalenceQuestionsController(QuestionService questionService, FileService fileService) {
        this.questionService = questionService;
        this.fileService = fileService;
    }

    @PutMapping("/gre-service/question/sentence-equivalence")
    @PreAuthorize("hasAuthority('GRE_CONTENT')")
    public SentenceEquivalenceQuestionDTO handle(@RequestBody SentenceEquivalenceQuestionDTO questionDTO) {
        SentenceEquivalenceQuestion question = questionDTO.convert(new SentenceEquivalenceQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileService.get(questionDTO.getImage()));
        question = (SentenceEquivalenceQuestion) questionService.save(question);
        questionDTO = SentenceEquivalenceQuestionDTO.valueOf(question);
        return questionDTO;
    }

}
