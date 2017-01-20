package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.SentenceEquivalenceQuestion;
import com.ztc.testcenter.dto.question.SentenceEquivalenceQuestionDTO;
import com.ztc.testcenter.repository.question.SentenceEquivalenceQuestionRepository;
import com.ztc.testcenter.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
@RequestMapping("/sentenceEquivalenceQuestions")
public class SentenceEquivalenceQuestionRestService implements QuestionRestService<SentenceEquivalenceQuestionDTO> {

    final private SentenceEquivalenceQuestionRepository repository;

    final private ManagerService managerService;

    @Autowired
    public SentenceEquivalenceQuestionRestService(SentenceEquivalenceQuestionRepository repository, ManagerService managerService) {
        this.repository = repository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<SentenceEquivalenceQuestionDTO> getQuestions(Pageable pageable) {
        return repository.findAll(pageable).map(SentenceEquivalenceQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public SentenceEquivalenceQuestionDTO save(@RequestBody SentenceEquivalenceQuestionDTO sentenceEquivalenceQuestionDTO) {
        SentenceEquivalenceQuestion question = sentenceEquivalenceQuestionDTO.convert();
        question = (SentenceEquivalenceQuestion) managerService.saveQuestion(question);
        return SentenceEquivalenceQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        managerService.deleteQuestion(id);
    }

}
