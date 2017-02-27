package com.ztc.testcenter.service;

import com.ztc.testcenter.domain.File;
import com.ztc.testcenter.domain.question.Question;
import com.ztc.testcenter.repository.FileRepository;
import com.ztc.testcenter.repository.question.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yubar on 1/19/2017.
 */

@Service
@Transactional
public class ManagerService {

    private final QuestionRepository questionRepository;
    private final FileRepository fileRepository;

    @Autowired
    public ManagerService(QuestionRepository questionRepository, FileRepository fileRepository) {
        this.questionRepository = questionRepository;
        this.fileRepository = fileRepository;
    }

    public File save(File file) {
        return fileRepository.save(file);
    }

    public Question save(Question question) {
        question.prepare();
        return questionRepository.save(question);
    }

    public void deleteQuestion(Long id) {
        questionRepository.delete(questionRepository.getOne(id));
    }

}
