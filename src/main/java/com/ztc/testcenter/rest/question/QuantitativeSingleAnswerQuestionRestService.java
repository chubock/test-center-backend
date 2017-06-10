package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.QuantitativeSingleAnswerQuestion;
import com.ztc.testcenter.dto.question.QuantitativeSingleAnswerQuestionDTO;
import com.ztc.testcenter.repository.FileRepository;
import com.ztc.testcenter.repository.question.QuantitativeSingleAnswerQuestionRepository;
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
@RequestMapping("/quantitativeSingleAnswerQuestions")
public class QuantitativeSingleAnswerQuestionRestService implements QuestionRestService<QuantitativeSingleAnswerQuestionDTO, QuantitativeSingleAnswerQuestion> {

    final private QuantitativeSingleAnswerQuestionRepository repository;
    final private FileRepository fileRepository;

    final private ManagerService managerService;

    @Autowired
    public QuantitativeSingleAnswerQuestionRestService(QuantitativeSingleAnswerQuestionRepository repository, FileRepository fileRepository, ManagerService managerService) {
        this.repository = repository;
        this.fileRepository = fileRepository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('GRE_QUANTITATIVE_SINGLE_ANSWER_QUESTION_REST_SERVICE__GET_QUESTIONS')")
    public Page<QuantitativeSingleAnswerQuestionDTO> getQuestions(@ModelAttribute QuestionSpecification<QuantitativeSingleAnswerQuestion> specification, Pageable pageable) {
        return repository.findAll(specification, pageable).map(QuantitativeSingleAnswerQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_QUANTITATIVE_SINGLE_ANSWER_QUESTION_REST_SERVICE__SAVE')")
    public QuantitativeSingleAnswerQuestionDTO save(@RequestBody QuantitativeSingleAnswerQuestionDTO questionDTO) {
        QuantitativeSingleAnswerQuestion question = questionDTO.convert(new QuantitativeSingleAnswerQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileRepository.getOne(questionDTO.getImage()));
        question = (QuantitativeSingleAnswerQuestion) managerService.save(question);
        return QuantitativeSingleAnswerQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('GRE_QUANTITATIVE_SINGLE_ANSWER_QUESTION_REST_SERVICE__DELETE')")
    public void delete(@PathVariable Long id) {
        managerService.deleteQuestion(id);
    }

}
