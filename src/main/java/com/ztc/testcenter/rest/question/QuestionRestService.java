package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.domain.question.Question;
import com.ztc.testcenter.dto.question.QuestionDTO;
import com.ztc.testcenter.specification.QuestionSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by Yubar on 1/20/2017.
 */
public interface QuestionRestService<T extends QuestionDTO, S extends Question> {

    Page<T> getQuestions(QuestionSpecification<S> specification, Pageable pageable);
    T save(T questionDto);
    void delete(Long id);

}
