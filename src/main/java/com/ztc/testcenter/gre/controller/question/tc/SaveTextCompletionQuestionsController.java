package com.ztc.testcenter.gre.controller.question.tc;

import com.ztc.testcenter.file.service.FileService;
import com.ztc.testcenter.gre.domain.question.TextCompletionQuestion;
import com.ztc.testcenter.gre.dto.TextCompletionQuestionDTO;
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
public class SaveTextCompletionQuestionsController {

    final private QuestionService questionService;
    final private FileService fileService;

    @Autowired
    public SaveTextCompletionQuestionsController(QuestionService questionService, FileService fileService) {
        this.questionService = questionService;
        this.fileService = fileService;
    }

    @PutMapping("/gre-service/question/text-completion")
    @PreAuthorize("hasAuthority('GRE_CONTENT')")
    public TextCompletionQuestionDTO handle(@RequestBody TextCompletionQuestionDTO questionDTO) {
        TextCompletionQuestion question = questionDTO.convert(new TextCompletionQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileService.get(questionDTO.getImage()));
        question = (TextCompletionQuestion) questionService.save(question);
        questionDTO = TextCompletionQuestionDTO.valueOf(question);
        return questionDTO;
    }

}
