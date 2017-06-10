package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.TextCompletionQuestion;
import com.ztc.testcenter.dto.question.TextCompletionQuestionDTO;
import com.ztc.testcenter.repository.FileRepository;
import com.ztc.testcenter.repository.question.TextCompletionQuestionRepository;
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
@RequestMapping("/textCompletionQuestions")
public class TextCompletionQuestionRestService implements QuestionRestService<TextCompletionQuestionDTO, TextCompletionQuestion> {

    final private TextCompletionQuestionRepository repository;
    final private FileRepository fileRepository;

    final private ManagerService managerService;

    @Autowired
    public TextCompletionQuestionRestService(TextCompletionQuestionRepository repository, FileRepository fileRepository, ManagerService managerService) {
        this.repository = repository;
        this.fileRepository = fileRepository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('GRE_TEXT_COMPLETION_QUESTION_REST_SERVICE__GET_QUESTIONS')")
    public Page<TextCompletionQuestionDTO> getQuestions(@ModelAttribute QuestionSpecification<TextCompletionQuestion> specification, Pageable pageable) {
        Page<TextCompletionQuestion> allWithItems = repository.findAll(specification, pageable);
        return allWithItems.map(TextCompletionQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_TEXT_COMPLETION_QUESTION_REST_SERVICE__SAVE')")
    public TextCompletionQuestionDTO save(@RequestBody TextCompletionQuestionDTO questionDTO) {
        TextCompletionQuestion question = questionDTO.convert(new TextCompletionQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileRepository.getOne(questionDTO.getImage()));
        question = (TextCompletionQuestion) managerService.save(question);
        question.getItems().forEach(item -> item.populateChoices());
        return TextCompletionQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('GRE_TEXT_COMPLETION_QUESTION_REST_SERVICE__DELETE')")
    public void delete(@PathVariable Long id) {
        managerService.deleteQuestion(id);
    }

}
