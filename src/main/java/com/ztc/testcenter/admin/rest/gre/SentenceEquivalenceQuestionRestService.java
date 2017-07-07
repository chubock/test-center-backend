package com.ztc.testcenter.admin.rest.gre;

import com.ztc.testcenter.question.gre.domain.SentenceEquivalenceQuestion;
import com.ztc.testcenter.question.gre.dto.SentenceEquivalenceQuestionDTO;
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
@RequestMapping("/admin/sentenceEquivalenceQuestions")
public class SentenceEquivalenceQuestionRestService implements QuestionRestService<SentenceEquivalenceQuestionDTO, SentenceEquivalenceQuestion> {

    final private FileService fileService;

    final private QuestionService questionService;

    @Autowired
    public SentenceEquivalenceQuestionRestService(FileService fileService, QuestionService questionService) {
        this.fileService = fileService;
        this.questionService = questionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('GRE_SENTENCE_EQUIVALENCE_QUESTION_REST_SERVICE__GET_QUESTIONS')")
    public Page<SentenceEquivalenceQuestionDTO> getQuestions(@ModelAttribute QuestionSpecification<SentenceEquivalenceQuestion> specification, Pageable pageable) {
        return questionService.findAllQuestions(specification, pageable, SentenceEquivalenceQuestion.class).map(SentenceEquivalenceQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_SENTENCE_EQUIVALENCE_QUESTION_REST_SERVICE__SAVE')")
    public SentenceEquivalenceQuestionDTO save(@RequestBody SentenceEquivalenceQuestionDTO questionDTO) {
        SentenceEquivalenceQuestion question = questionDTO.convert(new SentenceEquivalenceQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileService.get(questionDTO.getImage()));
        question = (SentenceEquivalenceQuestion) questionService.save(question);
        return SentenceEquivalenceQuestionDTO.valueOf(question);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('GRE_SENTENCE_EQUIVALENCE_QUESTION_REST_SERVICE__DELETE')")
    public void delete(@PathVariable Long id) {
        questionService.delete(id);
    }

}
