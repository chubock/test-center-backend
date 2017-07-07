package com.ztc.testcenter.admin.rest.gre;

import com.ztc.testcenter.question.gre.domain.WritingQuestion;
import com.ztc.testcenter.question.gre.dto.WritingQuestionDTO;
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
@RequestMapping("/admin/writingQuestions")
public class WritingQuestionRestService implements QuestionRestService<WritingQuestionDTO, WritingQuestion> {

    final private FileService fileService;

    final private QuestionService questionService;

    @Autowired
    public WritingQuestionRestService(FileService fileService, QuestionService questionService) {
        this.fileService = fileService;
        this.questionService = questionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('GRE_WRITING_QUESTION_REST_SERVICE__GET_QUESTIONS')")
    public Page<WritingQuestionDTO> getQuestions(@ModelAttribute QuestionSpecification<WritingQuestion> specification, Pageable pageable) {
        return questionService.findAllQuestions(specification, pageable, WritingQuestion.class).map(WritingQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_WRITING_QUESTION_REST_SERVICE__SAVE')")
    public WritingQuestionDTO save(@RequestBody WritingQuestionDTO questionDTO) {
        WritingQuestion question = questionDTO.convert(new WritingQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileService.get(questionDTO.getImage()));
        question = (WritingQuestion) questionService.save(question);
        return WritingQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('GRE_WRITING_QUESTION_REST_SERVICE__DELETE')")
    public void delete(@PathVariable Long id) {
        questionService.delete(id);
    }

}
