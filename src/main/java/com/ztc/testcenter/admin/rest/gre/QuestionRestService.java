package com.ztc.testcenter.admin.rest.gre;

import com.ztc.testcenter.question.gre.domain.Question;
import com.ztc.testcenter.question.gre.dto.QuestionDTO;
import com.ztc.testcenter.question.gre.specification.QuestionSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Yubar on 1/20/2017.
 */
public interface QuestionRestService<T extends QuestionDTO, S extends Question> {

    Page<T> getQuestions(QuestionSpecification<S> specification, Pageable pageable);
    T save(T questionDto);
    void delete(Long id);

}
