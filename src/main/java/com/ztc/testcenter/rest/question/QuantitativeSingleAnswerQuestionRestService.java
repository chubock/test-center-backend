package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.QuantitativeSingleAnswerQuestion;
import com.ztc.testcenter.dto.question.QuantitativeSingleAnswerQuestionDTO;
import com.ztc.testcenter.repository.question.QuantitativeSingleAnswerQuestionRepository;
import com.ztc.testcenter.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
@RequestMapping("/quantitativeSingleAnswerQuestions")
public class QuantitativeSingleAnswerQuestionRestService implements QuestionRestService<QuantitativeSingleAnswerQuestionDTO> {

    final private QuantitativeSingleAnswerQuestionRepository repository;

    final private ManagerService managerService;

    @Autowired
    public QuantitativeSingleAnswerQuestionRestService(QuantitativeSingleAnswerQuestionRepository repository, ManagerService managerService) {
        this.repository = repository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<QuantitativeSingleAnswerQuestionDTO> getQuestions(Pageable pageable) {
        return repository.findAll(pageable).map(QuantitativeSingleAnswerQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public QuantitativeSingleAnswerQuestionDTO save(@RequestBody QuantitativeSingleAnswerQuestionDTO sentenceEquivalenceQuestionDTO) {
        QuantitativeSingleAnswerQuestion question = sentenceEquivalenceQuestionDTO.convert();
        question = (QuantitativeSingleAnswerQuestion) managerService.saveQuestion(question);
        return QuantitativeSingleAnswerQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        managerService.deleteQuestion(id);
    }

}
