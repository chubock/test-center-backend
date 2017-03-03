package com.ztc.testcenter.domain.question;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Yubar on 3/1/2017.
 */

public interface QuestionsContainer {

    List<Question> innerQuestions();

}
