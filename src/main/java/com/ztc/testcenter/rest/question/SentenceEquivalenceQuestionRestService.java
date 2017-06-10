package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.SentenceEquivalenceQuestion;
import com.ztc.testcenter.dto.question.SentenceEquivalenceQuestionDTO;
import com.ztc.testcenter.repository.FileRepository;
import com.ztc.testcenter.repository.question.SentenceEquivalenceQuestionRepository;
import com.ztc.testcenter.service.ManagerService;
import com.ztc.testcenter.specification.QuestionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
@RequestMapping("/sentenceEquivalenceQuestions")
public class SentenceEquivalenceQuestionRestService implements QuestionRestService<SentenceEquivalenceQuestionDTO, SentenceEquivalenceQuestion> {

    final private SentenceEquivalenceQuestionRepository repository;
    final private FileRepository fileRepository;

    final private ManagerService managerService;

    @Autowired
    public SentenceEquivalenceQuestionRestService(SentenceEquivalenceQuestionRepository repository, FileRepository fileRepository, ManagerService managerService) {
        this.repository = repository;
        this.fileRepository = fileRepository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('GRE_SENTENCE_EQUIVALENCE_QUESTION_REST_SERVICE__GET_QUESTIONS')")
    public Page<SentenceEquivalenceQuestionDTO> getQuestions(@ModelAttribute QuestionSpecification<SentenceEquivalenceQuestion> specification, Pageable pageable) {
        return repository.findAll(specification, pageable).map(SentenceEquivalenceQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_SENTENCE_EQUIVALENCE_QUESTION_REST_SERVICE__SAVE')")
    public SentenceEquivalenceQuestionDTO save(@RequestBody SentenceEquivalenceQuestionDTO questionDTO) {
        SentenceEquivalenceQuestion question = questionDTO.convert(new SentenceEquivalenceQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileRepository.getOne(questionDTO.getImage()));
        question = (SentenceEquivalenceQuestion) managerService.save(question);
        return SentenceEquivalenceQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('GRE_SENTENCE_EQUIVALENCE_QUESTION_REST_SERVICE__DELETE')")
    public void delete(@PathVariable Long id) {
        managerService.deleteQuestion(id);
    }

}
