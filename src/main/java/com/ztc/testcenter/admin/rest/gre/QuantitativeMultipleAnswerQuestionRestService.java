package com.ztc.testcenter.admin.rest.gre;

import com.ztc.testcenter.question.gre.domain.QuantitativeMultipleAnswerQuestion;
import com.ztc.testcenter.question.gre.dto.QuantitativeMultipleAnswerQuestionDTO;
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
@RequestMapping("/admin/quantitativeMultipleAnswerQuestions")
public class QuantitativeMultipleAnswerQuestionRestService implements QuestionRestService<QuantitativeMultipleAnswerQuestionDTO, QuantitativeMultipleAnswerQuestion> {

    final private FileService fileService;

    final private QuestionService questionService;

    @Autowired
    public QuantitativeMultipleAnswerQuestionRestService(FileService fileService, QuestionService questionService) {
        this.fileService = fileService;
        this.questionService = questionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('GRE_QUANTITATIVE_MULTIPLE_ANSWER_QUESTION_REST_SERVICE__GET_QUESTIONS')")
    public Page<QuantitativeMultipleAnswerQuestionDTO> getQuestions(@ModelAttribute QuestionSpecification<QuantitativeMultipleAnswerQuestion> specification, Pageable pageable) {
        return questionService.findAllQuestions(specification, pageable, QuantitativeMultipleAnswerQuestion.class).map(QuantitativeMultipleAnswerQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_QUANTITATIVE_MULTIPLE_ANSWER_QUESTION_REST_SERVICE__SAVE')")
    public QuantitativeMultipleAnswerQuestionDTO save(@RequestBody QuantitativeMultipleAnswerQuestionDTO questionDTO) {
        QuantitativeMultipleAnswerQuestion question = questionDTO.convert(new QuantitativeMultipleAnswerQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileService.get(questionDTO.getImage()));
        question = (QuantitativeMultipleAnswerQuestion) questionService.save(question);
        return QuantitativeMultipleAnswerQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('GRE_QUANTITATIVE_MULTIPLE_ANSWER_QUESTION_REST_SERVICE__DELETE')")
    public void delete(@PathVariable Long id) {
        questionService.delete(id);
    }

}
