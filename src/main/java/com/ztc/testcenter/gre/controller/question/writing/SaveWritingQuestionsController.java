package com.ztc.testcenter.gre.controller.question.writing;

import com.ztc.testcenter.file.service.FileService;
import com.ztc.testcenter.gre.domain.question.WritingQuestion;
import com.ztc.testcenter.gre.dto.WritingQuestionDTO;
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
public class SaveWritingQuestionsController {

    final private QuestionService questionService;
    final private FileService fileService;

    @Autowired
    public SaveWritingQuestionsController(QuestionService questionService, FileService fileService) {
        this.questionService = questionService;
        this.fileService = fileService;
    }

    @PutMapping("/gre-service/question/writing")
    @PreAuthorize("hasAuthority('GRE_CONTENT')")
    public WritingQuestionDTO handle(@RequestBody WritingQuestionDTO questionDTO) {
        WritingQuestion question = questionDTO.convert(new WritingQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileService.get(questionDTO.getImage()));
        question = (WritingQuestion) questionService.save(question);
        questionDTO = WritingQuestionDTO.valueOf(question);
        return questionDTO;
    }

}
