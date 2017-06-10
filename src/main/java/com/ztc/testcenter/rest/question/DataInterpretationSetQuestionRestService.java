package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.DataInterpretationSetQuestion;
import com.ztc.testcenter.dto.question.DataInterpretationSetQuestionDTO;
import com.ztc.testcenter.repository.FileRepository;
import com.ztc.testcenter.repository.question.DataInterpretationSetQuestionRepository;
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
@RequestMapping("/dataInterpretationSetQuestions")
public class DataInterpretationSetQuestionRestService implements QuestionRestService<DataInterpretationSetQuestionDTO, DataInterpretationSetQuestion> {

    final private DataInterpretationSetQuestionRepository repository;
    final private FileRepository fileRepository;

    final private ManagerService managerService;

    @Autowired
    public DataInterpretationSetQuestionRestService(DataInterpretationSetQuestionRepository repository, FileRepository fileRepository, ManagerService managerService) {
        this.repository = repository;
        this.fileRepository = fileRepository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('GRE_DATA_INTERPRETATION_SET_QUESTION_REST_SERVICE__GET_QUESTIONS')")
    public Page<DataInterpretationSetQuestionDTO> getQuestions(@ModelAttribute QuestionSpecification<DataInterpretationSetQuestion> specification, Pageable pageable) {
        return repository.findAll(specification, pageable).map(DataInterpretationSetQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_DATA_INTERPRETATION_SET_QUESTION_REST_SERVICE__SAVE')")
    public DataInterpretationSetQuestionDTO save(@RequestBody DataInterpretationSetQuestionDTO questionDTO) {
        DataInterpretationSetQuestion question = questionDTO.convert(new DataInterpretationSetQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileRepository.getOne(questionDTO.getImage()));
        question = (DataInterpretationSetQuestion) managerService.save(question);
        questionDTO = DataInterpretationSetQuestionDTO.valueOf(question);
        return questionDTO;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('GRE_DATA_INTERPRETATION_SET_QUESTION_REST_SERVICE__DELETE')")
    public void delete(@PathVariable Long id) {
        managerService.deleteQuestion(id);
    }

}
