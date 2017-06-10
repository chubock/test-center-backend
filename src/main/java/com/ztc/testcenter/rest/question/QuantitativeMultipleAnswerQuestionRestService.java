package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.QuantitativeMultipleAnswerQuestion;
import com.ztc.testcenter.dto.question.QuantitativeMultipleAnswerQuestionDTO;
import com.ztc.testcenter.repository.FileRepository;
import com.ztc.testcenter.repository.question.QuantitativeMultipleAnswerQuestionRepository;
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
@RequestMapping("/quantitativeMultipleAnswerQuestions")
public class QuantitativeMultipleAnswerQuestionRestService implements QuestionRestService<QuantitativeMultipleAnswerQuestionDTO, QuantitativeMultipleAnswerQuestion> {

    final private QuantitativeMultipleAnswerQuestionRepository repository;
    final private FileRepository fileRepository;

    final private ManagerService managerService;

    @Autowired
    public QuantitativeMultipleAnswerQuestionRestService(QuantitativeMultipleAnswerQuestionRepository repository, FileRepository fileRepository, ManagerService managerService) {
        this.repository = repository;
        this.fileRepository = fileRepository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('GRE_QUANTITATIVE_MULTIPLE_ANSWER_QUESTION_REST_SERVICE__GET_QUESTIONS')")
    public Page<QuantitativeMultipleAnswerQuestionDTO> getQuestions(@ModelAttribute QuestionSpecification<QuantitativeMultipleAnswerQuestion> specification, Pageable pageable) {
        return repository.findAll(specification, pageable).map(QuantitativeMultipleAnswerQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_QUANTITATIVE_MULTIPLE_ANSWER_QUESTION_REST_SERVICE__SAVE')")
    public QuantitativeMultipleAnswerQuestionDTO save(@RequestBody QuantitativeMultipleAnswerQuestionDTO questionDTO) {
        QuantitativeMultipleAnswerQuestion question = questionDTO.convert(new QuantitativeMultipleAnswerQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileRepository.getOne(questionDTO.getImage()));
        question = (QuantitativeMultipleAnswerQuestion) managerService.save(question);
        return QuantitativeMultipleAnswerQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('GRE_QUANTITATIVE_MULTIPLE_ANSWER_QUESTION_REST_SERVICE__DELETE')")
    public void delete(@PathVariable Long id) {
        managerService.deleteQuestion(id);
    }

}
