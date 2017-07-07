package com.ztc.testcenter.admin.rest.gre;

import com.ztc.testcenter.question.gre.domain.DataInterpretationSetQuestion;
import com.ztc.testcenter.question.gre.dto.DataInterpretationSetQuestionDTO;
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
@RequestMapping("/admin/dataInterpretationSetQuestions")
public class DataInterpretationSetQuestionRestService implements QuestionRestService<DataInterpretationSetQuestionDTO, DataInterpretationSetQuestion> {

    final private FileService fileService;

    final private QuestionService questionService;

    @Autowired
    public DataInterpretationSetQuestionRestService(FileService fileService, QuestionService questionService) {
        this.fileService = fileService;
        this.questionService = questionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('GRE_DATA_INTERPRETATION_SET_QUESTION_REST_SERVICE__GET_QUESTIONS')")
    public Page<DataInterpretationSetQuestionDTO> getQuestions(@ModelAttribute QuestionSpecification<DataInterpretationSetQuestion> specification, Pageable pageable) {
        return questionService.findAllQuestions(specification, pageable, DataInterpretationSetQuestion.class).map(DataInterpretationSetQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_DATA_INTERPRETATION_SET_QUESTION_REST_SERVICE__SAVE')")
    public DataInterpretationSetQuestionDTO save(@RequestBody DataInterpretationSetQuestionDTO questionDTO) {
        DataInterpretationSetQuestion question = questionDTO.convert(new DataInterpretationSetQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileService.get(questionDTO.getImage()));
        question = (DataInterpretationSetQuestion) questionService.save(question);
        questionDTO = DataInterpretationSetQuestionDTO.valueOf(question);
        return questionDTO;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('GRE_DATA_INTERPRETATION_SET_QUESTION_REST_SERVICE__DELETE')")
    public void delete(@PathVariable Long id) {
        questionService.delete(id);
    }

}
