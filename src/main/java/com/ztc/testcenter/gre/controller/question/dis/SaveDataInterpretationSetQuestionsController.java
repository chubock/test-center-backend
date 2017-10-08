package com.ztc.testcenter.gre.controller.question.dis;

import com.ztc.testcenter.file.service.FileService;
import com.ztc.testcenter.gre.domain.question.DataInterpretationSetQuestion;
import com.ztc.testcenter.gre.dto.DataInterpretationSetQuestionDTO;
import com.ztc.testcenter.gre.service.QuestionService;
import com.ztc.testcenter.gre.specification.QuestionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
public class SaveDataInterpretationSetQuestionsController {

    final private QuestionService questionService;
    final private FileService fileService;

    @Autowired
    public SaveDataInterpretationSetQuestionsController(QuestionService questionService, FileService fileService) {
        this.questionService = questionService;
        this.fileService = fileService;
    }

    @PutMapping("/gre-service/question/data-interpretation-set")
    @PreAuthorize("hasAuthority('GRE_CONTENT')")
    public DataInterpretationSetQuestionDTO handle(@RequestBody DataInterpretationSetQuestionDTO questionDTO) {
        DataInterpretationSetQuestion question = questionDTO.convert(new DataInterpretationSetQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileService.get(questionDTO.getImage()));
        question = (DataInterpretationSetQuestion) questionService.save(question);
        questionDTO = DataInterpretationSetQuestionDTO.valueOf(question);
        return questionDTO;
    }

}
