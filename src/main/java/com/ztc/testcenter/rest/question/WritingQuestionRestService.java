package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.WritingQuestion;
import com.ztc.testcenter.dto.question.WritingQuestionDTO;
import com.ztc.testcenter.repository.FileRepository;
import com.ztc.testcenter.repository.question.WritingQuestionRepository;
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
@RequestMapping("/writingQuestions")
public class WritingQuestionRestService implements QuestionRestService<WritingQuestionDTO, WritingQuestion> {

    final private WritingQuestionRepository repository;
    final private FileRepository fileRepository;

    final private ManagerService managerService;

    @Autowired
    public WritingQuestionRestService(WritingQuestionRepository repository, FileRepository fileRepository, ManagerService managerService) {
        this.repository = repository;
        this.fileRepository = fileRepository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('GRE_WRITING_QUESTION_REST_SERVICE__GET_QUESTIONS')")
    public Page<WritingQuestionDTO> getQuestions(@ModelAttribute QuestionSpecification<WritingQuestion> specification, Pageable pageable) {
        return repository.findAll(specification, pageable).map(WritingQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_WRITING_QUESTION_REST_SERVICE__SAVE')")
    public WritingQuestionDTO save(@RequestBody WritingQuestionDTO questionDTO) {
        WritingQuestion question = questionDTO.convert(new WritingQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileRepository.getOne(questionDTO.getImage()));
        question = (WritingQuestion) managerService.save(question);
        return WritingQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('GRE_WRITING_QUESTION_REST_SERVICE__DELETE')")
    public void delete(@PathVariable Long id) {
        managerService.deleteQuestion(id);
    }

}
