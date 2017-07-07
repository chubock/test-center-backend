package com.ztc.testcenter.admin.rest.gre;

import com.ztc.testcenter.question.gre.domain.TextCompletionQuestion;
import com.ztc.testcenter.question.gre.dto.TextCompletionQuestionDTO;
import com.ztc.testcenter.file.service.FileService;
import com.ztc.testcenter.question.gre.service.QuestionService;
import com.ztc.testcenter.question.gre.specification.QuestionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
@RequestMapping("/admin/textCompletionQuestions")
public class TextCompletionQuestionRestService implements QuestionRestService<TextCompletionQuestionDTO, TextCompletionQuestion> {

    final private FileService fileService;

    final private QuestionService questionService;

    @Autowired
    public TextCompletionQuestionRestService(FileService fileService, QuestionService questionService) {
        this.fileService = fileService;
        this.questionService = questionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('GRE_TEXT_COMPLETION_QUESTION_REST_SERVICE__GET_QUESTIONS')")
    public Page<TextCompletionQuestionDTO> getQuestions(@ModelAttribute QuestionSpecification<TextCompletionQuestion> specification, Pageable pageable) {
        Page<TextCompletionQuestion> allWithItems = questionService.findAllQuestions(specification, pageable, TextCompletionQuestion.class);
        return allWithItems.map(TextCompletionQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_TEXT_COMPLETION_QUESTION_REST_SERVICE__SAVE')")
    public TextCompletionQuestionDTO save(@RequestBody TextCompletionQuestionDTO questionDTO) {
        TextCompletionQuestion question = questionDTO.convert(new TextCompletionQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileService.get(questionDTO.getImage()));
        question = (TextCompletionQuestion) questionService.save(question);
        question.getItems().forEach(item -> item.populateChoices());
        return TextCompletionQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('GRE_TEXT_COMPLETION_QUESTION_REST_SERVICE__DELETE')")
    public void delete(@PathVariable Long id) {
        questionService.delete(id);
    }

}
