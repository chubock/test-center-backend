package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.SelectInPassageQuestion;
import com.ztc.testcenter.dto.question.SelectInPassageQuestionDTO;
import com.ztc.testcenter.repository.question.SelectInPassageQuestionRepository;
import com.ztc.testcenter.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
@RequestMapping("/selectInPassageQuestions")
public class SelectInPassageQuestionRestService implements QuestionRestService<SelectInPassageQuestionDTO> {

    final private SelectInPassageQuestionRepository repository;

    final private ManagerService managerService;

    @Autowired
    public SelectInPassageQuestionRestService(SelectInPassageQuestionRepository repository, ManagerService managerService) {
        this.repository = repository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<SelectInPassageQuestionDTO> getQuestions(Pageable pageable) {
        return repository.findAll(pageable).map(SelectInPassageQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public SelectInPassageQuestionDTO save(@RequestBody SelectInPassageQuestionDTO questionDTO) {
        SelectInPassageQuestion question = questionDTO.convert();
        question = (SelectInPassageQuestion) managerService.saveQuestion(question);
        return SelectInPassageQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        managerService.deleteQuestion(id);
    }

}
