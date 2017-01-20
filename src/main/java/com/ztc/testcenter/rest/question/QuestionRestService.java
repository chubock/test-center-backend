package com.ztc.testcenter.rest.question;

import com.ztc.testcenter.dto.question.QuestionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Yubar on 1/20/2017.
 */
public interface QuestionRestService<T extends QuestionDTO> {

    Page<T> getQuestions(Pageable pageable);
    T save(T questionDto);
    void delete(long id);

}
