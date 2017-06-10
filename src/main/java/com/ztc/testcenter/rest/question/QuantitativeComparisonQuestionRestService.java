package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.QuantitativeComparisonQuestion;
import com.ztc.testcenter.dto.question.QuantitativeComparisonQuestionDTO;
import com.ztc.testcenter.repository.FileRepository;
import com.ztc.testcenter.repository.question.QuantitativeComparisonQuestionRepository;
import com.ztc.testcenter.service.ManagerService;
import com.ztc.testcenter.specification.QuestionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
@RequestMapping("/quantitativeComparisonQuestions")
public class QuantitativeComparisonQuestionRestService implements QuestionRestService<QuantitativeComparisonQuestionDTO, QuantitativeComparisonQuestion> {

    final private QuantitativeComparisonQuestionRepository repository;
    final private FileRepository fileRepository;

    final private ManagerService managerService;

    @Autowired
    public QuantitativeComparisonQuestionRestService(QuantitativeComparisonQuestionRepository repository, FileRepository fileRepository, ManagerService managerService) {
        this.repository = repository;
        this.fileRepository = fileRepository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('GRE_QUANTITATIVE_COMPARISON_QUESTION_REST_SERVICE__GET_QUESTIONS')")
    public Page<QuantitativeComparisonQuestionDTO> getQuestions(@ModelAttribute QuestionSpecification<QuantitativeComparisonQuestion> specification, Pageable pageable) {
        return repository.findAll(specification, pageable).map(QuantitativeComparisonQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_QUANTITATIVE_COMPARISON_QUESTION_REST_SERVICE__SAVE')")
    public QuantitativeComparisonQuestionDTO save(@RequestBody QuantitativeComparisonQuestionDTO questionDTO) {
        QuantitativeComparisonQuestion question = questionDTO.convert(new QuantitativeComparisonQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileRepository.getOne(questionDTO.getImage()));
        question = (QuantitativeComparisonQuestion) managerService.save(question);
        return QuantitativeComparisonQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('GRE_QUANTITATIVE_COMPARISON_QUESTION_REST_SERVICE__DELETE')")
    public void delete(@PathVariable Long id) {
        managerService.deleteQuestion(id);
    }

}
