package com.ztc.testcenter.admin.rest.gre;

import com.ztc.testcenter.question.gre.domain.QuantitativeComparisonQuestion;
import com.ztc.testcenter.question.gre.dto.QuantitativeComparisonQuestionDTO;
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
@RequestMapping("/admin/quantitativeComparisonQuestions")
public class QuantitativeComparisonQuestionRestService implements QuestionRestService<QuantitativeComparisonQuestionDTO, QuantitativeComparisonQuestion> {

    final private FileService fileService;

    final private QuestionService questionService;

    @Autowired
    public QuantitativeComparisonQuestionRestService(FileService fileService, QuestionService questionService) {
        this.fileService = fileService;
        this.questionService = questionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('GRE_QUANTITATIVE_COMPARISON_QUESTION_REST_SERVICE__GET_QUESTIONS')")
    public Page<QuantitativeComparisonQuestionDTO> getQuestions(@ModelAttribute QuestionSpecification<QuantitativeComparisonQuestion> specification, Pageable pageable) {
        return questionService.findAllQuestions(specification, pageable, QuantitativeComparisonQuestion.class).map(QuantitativeComparisonQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_QUANTITATIVE_COMPARISON_QUESTION_REST_SERVICE__SAVE')")
    public QuantitativeComparisonQuestionDTO save(@RequestBody QuantitativeComparisonQuestionDTO questionDTO) {
        QuantitativeComparisonQuestion question = questionDTO.convert(new QuantitativeComparisonQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileService.get(questionDTO.getImage()));
        question = (QuantitativeComparisonQuestion) questionService.save(question);
        return QuantitativeComparisonQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('GRE_QUANTITATIVE_COMPARISON_QUESTION_REST_SERVICE__DELETE')")
    public void delete(@PathVariable Long id) {
        questionService.delete(id);
    }

}
