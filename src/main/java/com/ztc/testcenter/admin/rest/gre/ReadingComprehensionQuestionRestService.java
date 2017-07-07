package com.ztc.testcenter.admin.rest.gre;

import com.ztc.testcenter.question.gre.domain.ReadingComprehensionQuestion;
import com.ztc.testcenter.question.gre.dto.ReadingComprehensionQuestionDTO;
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
@RequestMapping("/admin/readingComprehensionQuestions")
public class ReadingComprehensionQuestionRestService implements QuestionRestService<ReadingComprehensionQuestionDTO, ReadingComprehensionQuestion> {

    final private FileService fileService;

    final private QuestionService questionService;

    @Autowired
    public ReadingComprehensionQuestionRestService(FileService fileService, QuestionService questionService) {
        this.fileService = fileService;
        this.questionService = questionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('GRE_READING_COMPREHENSION_QUESTION_REST_SERVICE__GET_QUESTIONS')")
    public Page<ReadingComprehensionQuestionDTO> getQuestions(@ModelAttribute QuestionSpecification<ReadingComprehensionQuestion> specification, Pageable pageable) {
        return questionService.findAllQuestions(specification, pageable, ReadingComprehensionQuestion.class).map(ReadingComprehensionQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_READING_COMPREHENSION_QUESTION_REST_SERVICE__GET_SAVE')")
    public ReadingComprehensionQuestionDTO save(@RequestBody ReadingComprehensionQuestionDTO questionDTO) {
        ReadingComprehensionQuestion question = questionDTO.convert(new ReadingComprehensionQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileService.get(questionDTO.getImage()));
        question = (ReadingComprehensionQuestion) questionService.save(question);
        questionDTO = ReadingComprehensionQuestionDTO.valueOf(question);
        return questionDTO;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('GRE_READING_COMPREHENSION_QUESTION_REST_SERVICE__GET_DELETE')")
    public void delete(@PathVariable Long id) {
        questionService.delete(id);
    }

}
