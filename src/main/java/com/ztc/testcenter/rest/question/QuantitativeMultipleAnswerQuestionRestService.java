package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.QuantitativeMultipleAnswerQuestion;
import com.ztc.testcenter.dto.question.QuantitativeMultipleAnswerQuestionDTO;
import com.ztc.testcenter.repository.question.QuantitativeMultipleAnswerQuestionRepository;
import com.ztc.testcenter.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
@RequestMapping("/quantitativeMultipleAnswerQuestions")
public class QuantitativeMultipleAnswerQuestionRestService implements QuestionRestService<QuantitativeMultipleAnswerQuestionDTO> {

    final private QuantitativeMultipleAnswerQuestionRepository repository;

    final private ManagerService managerService;

    @Autowired
    public QuantitativeMultipleAnswerQuestionRestService(QuantitativeMultipleAnswerQuestionRepository repository, ManagerService managerService) {
        this.repository = repository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<QuantitativeMultipleAnswerQuestionDTO> getQuestions(Pageable pageable) {
        return repository.findAll(pageable).map(QuantitativeMultipleAnswerQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public QuantitativeMultipleAnswerQuestionDTO save(@RequestBody QuantitativeMultipleAnswerQuestionDTO questionDTO) {
        QuantitativeMultipleAnswerQuestion question = questionDTO.convert();
        question = (QuantitativeMultipleAnswerQuestion) managerService.saveQuestion(question);
        return QuantitativeMultipleAnswerQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        managerService.deleteQuestion(id);
    }

}
