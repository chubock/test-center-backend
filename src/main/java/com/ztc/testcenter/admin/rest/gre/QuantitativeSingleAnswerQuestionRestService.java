package com.ztc.testcenter.admin.rest.gre;

import com.ztc.testcenter.question.gre.domain.QuantitativeSingleAnswerQuestion;
import com.ztc.testcenter.question.gre.dto.QuantitativeSingleAnswerQuestionDTO;
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
@RequestMapping("/admin/quantitativeSingleAnswerQuestions")
public class QuantitativeSingleAnswerQuestionRestService implements QuestionRestService<QuantitativeSingleAnswerQuestionDTO, QuantitativeSingleAnswerQuestion> {

    final private FileService fileService;

    final private QuestionService questionService;

    @Autowired
    public QuantitativeSingleAnswerQuestionRestService(FileService fileService, QuestionService questionService) {
        this.fileService = fileService;
        this.questionService = questionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('GRE_QUANTITATIVE_SINGLE_ANSWER_QUESTION_REST_SERVICE__GET_QUESTIONS')")
    public Page<QuantitativeSingleAnswerQuestionDTO> getQuestions(@ModelAttribute QuestionSpecification<QuantitativeSingleAnswerQuestion> specification, Pageable pageable) {
        return questionService.findAllQuestions(specification, pageable, QuantitativeSingleAnswerQuestion.class).map(QuantitativeSingleAnswerQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_QUANTITATIVE_SINGLE_ANSWER_QUESTION_REST_SERVICE__SAVE')")
    public QuantitativeSingleAnswerQuestionDTO save(@RequestBody QuantitativeSingleAnswerQuestionDTO questionDTO) {
        QuantitativeSingleAnswerQuestion question = questionDTO.convert(new QuantitativeSingleAnswerQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileService.get(questionDTO.getImage()));
        question = (QuantitativeSingleAnswerQuestion) questionService.save(question);
        return QuantitativeSingleAnswerQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('GRE_QUANTITATIVE_SINGLE_ANSWER_QUESTION_REST_SERVICE__DELETE')")
    public void delete(@PathVariable Long id) {
        questionService.delete(id);
    }

}
