package com.ztc.testcenter.gre.controller.question.rc;

import com.ztc.testcenter.file.service.FileService;
import com.ztc.testcenter.gre.domain.question.ReadingComprehensionQuestion;
import com.ztc.testcenter.gre.dto.ReadingComprehensionQuestionDTO;
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
public class SaveReadingComprehensionQuestionsController {

    final private QuestionService questionService;
    final private FileService fileService;

    @Autowired
    public SaveReadingComprehensionQuestionsController(QuestionService questionService, FileService fileService) {
        this.questionService = questionService;
        this.fileService = fileService;
    }

    @PutMapping("/gre-service/question/reading-comprehension")
    @PreAuthorize("hasAuthority('GRE_CONTENT')")
    public ReadingComprehensionQuestionDTO handle(@RequestBody ReadingComprehensionQuestionDTO questionDTO) {
        ReadingComprehensionQuestion question = questionDTO.convert(new ReadingComprehensionQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileService.get(questionDTO.getImage()));
        question = (ReadingComprehensionQuestion) questionService.save(question);
        questionDTO = ReadingComprehensionQuestionDTO.valueOf(question);
        return questionDTO;
    }

}
