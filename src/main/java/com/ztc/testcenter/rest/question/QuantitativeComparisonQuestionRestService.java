package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.QuantitativeComparisonQuestion;
import com.ztc.testcenter.dto.question.QuantitativeComparisonQuestionDTO;
import com.ztc.testcenter.repository.question.QuantitativeComparisonQuestionRepository;
import com.ztc.testcenter.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
@RequestMapping("/quantitativeComparisonQuestions")
public class QuantitativeComparisonQuestionRestService implements QuestionRestService<QuantitativeComparisonQuestionDTO> {

    final private QuantitativeComparisonQuestionRepository repository;

    final private ManagerService managerService;

    @Autowired
    public QuantitativeComparisonQuestionRestService(QuantitativeComparisonQuestionRepository repository, ManagerService managerService) {
        this.repository = repository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<QuantitativeComparisonQuestionDTO> getQuestions(Pageable pageable) {
        return repository.findAll(pageable).map(QuantitativeComparisonQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public QuantitativeComparisonQuestionDTO save(@RequestBody QuantitativeComparisonQuestionDTO questionDTO) {
        QuantitativeComparisonQuestion question = questionDTO.convert();
        question = (QuantitativeComparisonQuestion) managerService.saveQuestion(question);
        return QuantitativeComparisonQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        managerService.deleteQuestion(id);
    }

}
