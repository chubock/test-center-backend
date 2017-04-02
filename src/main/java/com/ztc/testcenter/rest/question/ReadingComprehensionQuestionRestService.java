package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.ReadingComprehensionQuestion;
import com.ztc.testcenter.dto.question.ReadingComprehensionQuestionDTO;
import com.ztc.testcenter.repository.FileRepository;
import com.ztc.testcenter.repository.question.ReadingComprehensionQuestionRepository;
import com.ztc.testcenter.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Yubar on 1/19/2017.
 */

@RestController
@RequestMapping("/readingComprehensionQuestions")
public class ReadingComprehensionQuestionRestService implements QuestionRestService<ReadingComprehensionQuestionDTO> {

    final private ReadingComprehensionQuestionRepository repository;
    final private FileRepository fileRepository;

    final private ManagerService managerService;

    @Autowired
    public ReadingComprehensionQuestionRestService(ReadingComprehensionQuestionRepository repository, FileRepository fileRepository, ManagerService managerService) {
        this.repository = repository;
        this.fileRepository = fileRepository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<ReadingComprehensionQuestionDTO> getQuestions(Pageable pageable) {
        return repository.findAll(pageable).map(ReadingComprehensionQuestionDTO::valueOf);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ReadingComprehensionQuestionDTO save(@RequestBody ReadingComprehensionQuestionDTO questionDTO) {
        ReadingComprehensionQuestion question = questionDTO.convert(new ReadingComprehensionQuestion());
        if (questionDTO.getImage() != null)
            question.setImage(fileRepository.getOne(questionDTO.getImage()));
        question = (ReadingComprehensionQuestion) managerService.save(question);
        questionDTO = ReadingComprehensionQuestionDTO.valueOf(question);
        return questionDTO;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        managerService.deleteQuestion(id);
    }

}
