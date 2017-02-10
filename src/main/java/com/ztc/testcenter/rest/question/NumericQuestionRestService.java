package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.NumericQuestion;
import com.ztc.testcenter.dto.question.NumericQuestionDTO;
import com.ztc.testcenter.repository.question.NumericQuestionRepository;
import com.ztc.testcenter.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
@RequestMapping("/numericQuestions")
public class NumericQuestionRestService implements QuestionRestService<NumericQuestionDTO> {

    final private NumericQuestionRepository repository;

    final private ManagerService managerService;

    @Autowired
    public NumericQuestionRestService(NumericQuestionRepository repository, ManagerService managerService) {
        this.repository = repository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<NumericQuestionDTO> getQuestions(Pageable pageable) {
        return repository.findAll(pageable).map(NumericQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public NumericQuestionDTO save(@RequestBody NumericQuestionDTO questionDTO) {
        NumericQuestion question = questionDTO.convert();
        question = (NumericQuestion) managerService.save(question);
        return NumericQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        managerService.deleteQuestion(id);
    }

}
