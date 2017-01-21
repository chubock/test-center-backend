package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.WritingQuestion;
import com.ztc.testcenter.dto.question.WritingQuestionDTO;
import com.ztc.testcenter.repository.question.WritingQuestionRepository;
import com.ztc.testcenter.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
@RequestMapping("/writingQuestions")
public class WritingQuestionRestService implements QuestionRestService<WritingQuestionDTO> {

    final private WritingQuestionRepository repository;

    final private ManagerService managerService;

    @Autowired
    public WritingQuestionRestService(WritingQuestionRepository repository, ManagerService managerService) {
        this.repository = repository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<WritingQuestionDTO> getQuestions(Pageable pageable) {
        return repository.findAll(pageable).map(WritingQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public WritingQuestionDTO save(@RequestBody WritingQuestionDTO questionDTO) {
        WritingQuestion question = questionDTO.convert();
        question = (WritingQuestion) managerService.saveQuestion(question);
        return WritingQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        managerService.deleteQuestion(id);
    }

}
