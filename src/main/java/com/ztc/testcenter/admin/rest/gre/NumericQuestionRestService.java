package com.ztc.testcenter.admin.rest.gre;

import com.ztc.testcenter.question.gre.domain.NumericQuestion;
import com.ztc.testcenter.question.gre.dto.NumericQuestionDTO;
import com.ztc.testcenter.file.service.FileService;
import com.ztc.testcenter.question.gre.service.QuestionService;
import com.ztc.testcenter.question.gre.specification.QuestionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
@RequestMapping("/admin/numericQuestions")
public class NumericQuestionRestService implements QuestionRestService<NumericQuestionDTO, NumericQuestion> {

    final private FileService fileService;

    final private QuestionService questionService;

    @Autowired
    public NumericQuestionRestService(FileService fileService, QuestionService questionService) {
        this.fileService = fileService;
        this.questionService = questionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('GRE_NUMERIC_QUESTION_REST_SERVICE__GET_QUESTIONS')")
    public Page<NumericQuestionDTO> getQuestions(@ModelAttribute QuestionSpecification<NumericQuestion> specification, Pageable pageable) {
        return questionService.findAllQuestions(specification, pageable, NumericQuestion.class).map(NumericQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_NUMERIC_QUESTION_REST_SERVICE__SAVE')")
    public NumericQuestionDTO save(@RequestBody NumericQuestionDTO questionDTO) {
        NumericQuestion question = questionDTO.convert(new NumericQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileService.get(questionDTO.getImage()));
        question = (NumericQuestion) questionService.save(question);
        return NumericQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('GRE_NUMERIC_QUESTION_REST_SERVICE__DELETE')")
    public void delete(@PathVariable Long id) {
        questionService.delete(id);
    }

}
