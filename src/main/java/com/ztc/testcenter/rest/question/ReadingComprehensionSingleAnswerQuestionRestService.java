package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.ReadingComprehensionSingleAnswerQuestion;
import com.ztc.testcenter.dto.question.ReadingComprehensionSingleAnswerQuestionDTO;
import com.ztc.testcenter.repository.question.ReadingComprehensionSingleAnswerQuestionRepository;
import com.ztc.testcenter.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
@RequestMapping("/readingComprehensionSingleAnswerQuestions")
public class ReadingComprehensionSingleAnswerQuestionRestService implements QuestionRestService<ReadingComprehensionSingleAnswerQuestionDTO> {

    final private ReadingComprehensionSingleAnswerQuestionRepository repository;

    final private ManagerService managerService;

    @Autowired
    public ReadingComprehensionSingleAnswerQuestionRestService(ReadingComprehensionSingleAnswerQuestionRepository repository, ManagerService managerService) {
        this.repository = repository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<ReadingComprehensionSingleAnswerQuestionDTO> getQuestions(Pageable pageable) {
        return repository.findAll(pageable).map(ReadingComprehensionSingleAnswerQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ReadingComprehensionSingleAnswerQuestionDTO save(@RequestBody ReadingComprehensionSingleAnswerQuestionDTO sentenceEquivalenceQuestionDTO) {
        ReadingComprehensionSingleAnswerQuestion question = sentenceEquivalenceQuestionDTO.convert();
        question = (ReadingComprehensionSingleAnswerQuestion) managerService.saveQuestion(question);
        return ReadingComprehensionSingleAnswerQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        managerService.deleteQuestion(id);
    }

}
