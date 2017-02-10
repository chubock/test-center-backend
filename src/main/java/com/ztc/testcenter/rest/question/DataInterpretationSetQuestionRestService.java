package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.DataInterpretationMultipleAnswerQuestion;
import com.ztc.testcenter.domain.question.DataInterpretationSetQuestion;
import com.ztc.testcenter.dto.question.DataInterpretationMultipleAnswerQuestionDTO;
import com.ztc.testcenter.dto.question.DataInterpretationNumericQuestionDTO;
import com.ztc.testcenter.dto.question.DataInterpretationSetQuestionDTO;
import com.ztc.testcenter.dto.question.DataInterpretationSingleAnswerQuestionDTO;
import com.ztc.testcenter.repository.question.DataInterpretationSetQuestionRepository;
import com.ztc.testcenter.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
@RequestMapping("/dataInterpretationSetQuestions")
public class DataInterpretationSetQuestionRestService implements QuestionRestService<DataInterpretationSetQuestionDTO> {

    final private DataInterpretationSetQuestionRepository repository;

    final private ManagerService managerService;

    @Autowired
    public DataInterpretationSetQuestionRestService(DataInterpretationSetQuestionRepository repository, ManagerService managerService) {
        this.repository = repository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<DataInterpretationSetQuestionDTO> getQuestions(Pageable pageable) {
        return repository.findAll(pageable).map(DataInterpretationSetQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public DataInterpretationSetQuestionDTO save(@RequestBody DataInterpretationSetQuestionDTO questionDTO) {
        DataInterpretationSetQuestion question = questionDTO.convert();
        question = (DataInterpretationSetQuestion) managerService.save(question);
        questionDTO = DataInterpretationSetQuestionDTO.valueOf(question);
        return questionDTO;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        managerService.deleteQuestion(id);
    }

}
