package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.ReadingComprehensionMultipleAnswerQuestion;
import com.ztc.testcenter.dto.question.ReadingComprehensionMultipleAnswerQuestionDTO;
import com.ztc.testcenter.repository.question.ReadingComprehensionMultipleAnswerQuestionRepository;
import com.ztc.testcenter.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
@RequestMapping("/readingComprehensionMultipleAnswerQuestions")
public class ReadingComprehensionMultipleAnswerQuestionRestService implements QuestionRestService<ReadingComprehensionMultipleAnswerQuestionDTO> {

    final private ReadingComprehensionMultipleAnswerQuestionRepository repository;

    final private ManagerService managerService;

    @Autowired
    public ReadingComprehensionMultipleAnswerQuestionRestService(ReadingComprehensionMultipleAnswerQuestionRepository repository, ManagerService managerService) {
        this.repository = repository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<ReadingComprehensionMultipleAnswerQuestionDTO> getQuestions(Pageable pageable) {
        return repository.findAll(pageable).map(ReadingComprehensionMultipleAnswerQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ReadingComprehensionMultipleAnswerQuestionDTO save(@RequestBody ReadingComprehensionMultipleAnswerQuestionDTO questionDTO) {
        ReadingComprehensionMultipleAnswerQuestion question = questionDTO.convert();
        question = (ReadingComprehensionMultipleAnswerQuestion) managerService.saveQuestion(question);
        return ReadingComprehensionMultipleAnswerQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        managerService.deleteQuestion(id);
    }

}
