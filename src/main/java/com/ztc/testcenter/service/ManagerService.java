package com.ztc.testcenter.service;

import com.ztc.testcenter.domain.File;
import com.ztc.testcenter.domain.question.Question;
import com.ztc.testcenter.domain.question.QuestionsContainer;
import com.ztc.testcenter.domain.question.QuestionTemplate;
import com.ztc.testcenter.repository.FileRepository;
import com.ztc.testcenter.repository.question.QuestionRepository;
import com.ztc.testcenter.repository.question.QuestionTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Created by Yubar on 1/19/2017.
 */

@Service
@Transactional
public class ManagerService {

    private final QuestionRepository questionRepository;
    private final QuestionTemplateRepository questionTemplateRepository;
    private final FileRepository fileRepository;

    @Autowired
    public ManagerService(QuestionRepository questionRepository, QuestionTemplateRepository questionTemplateRepository, FileRepository fileRepository) {
        this.questionRepository = questionRepository;
        this.questionTemplateRepository = questionTemplateRepository;
        this.fileRepository = fileRepository;
    }

    public File save(File file) {
        return fileRepository.save(file);
    }

    public Question save(Question question) {
        if (question instanceof QuestionsContainer)
            saveTemplate((QuestionsContainer) question);
        question.prepare();
        return questionRepository.save(question);
    }

    private void saveTemplate(QuestionsContainer questionsContainer) {
        Question question = (Question) questionsContainer;
        QuestionTemplate template = QuestionTemplate.templateOf(questionsContainer);
        QuestionTemplate newTemplate = questionTemplateRepository.findByLabel(template.getLabel());
        QuestionTemplate currentTemplate = question.getId() == null || question.getId() == 0 ? null : question.getTemplate();
        if (newTemplate == null)
            question.setTemplate(newTemplate = questionTemplateRepository.save(template));
        else
            question.setTemplate(newTemplate);
        if (currentTemplate == null || !Objects.equals(newTemplate.getId(), currentTemplate.getId())) {
            newTemplate.setCount(newTemplate.getCount() + 1);
            if (currentTemplate != null)
                currentTemplate.setCount(currentTemplate.getCount() - 1);
        }
    }

    public void deleteQuestion(Long id) {
        questionRepository.delete(questionRepository.getOne(id));
    }

}
