package com.ztc.testcenter.gre.controller.question.numeric;

import com.ztc.testcenter.file.service.FileService;
import com.ztc.testcenter.gre.domain.question.NumericQuestion;
import com.ztc.testcenter.gre.dto.NumericQuestionDTO;
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
public class SaveNumericQuestionsController {

    final private QuestionService questionService;
    final private FileService fileService;

    @Autowired
    public SaveNumericQuestionsController(QuestionService questionService, FileService fileService) {
        this.questionService = questionService;
        this.fileService = fileService;
    }

    @PutMapping("/gre-service/question/numeric")
    @PreAuthorize("hasAuthority('GRE_CONTENT')")
    public NumericQuestionDTO handle(@RequestBody NumericQuestionDTO questionDTO) {
        NumericQuestion question = questionDTO.convert(new NumericQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileService.get(questionDTO.getImage()));
        question = (NumericQuestion) questionService.save(question);
        questionDTO = NumericQuestionDTO.valueOf(question);
        return questionDTO;
    }

}
