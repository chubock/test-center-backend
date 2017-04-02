package com.ztc.testcenter.rest.test;

import com.ztc.testcenter.dto.test.TestDTO;
import com.ztc.testcenter.dto.test.TestSectionDTO;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.List;

/**
 * Created by Yubar on 3/4/2017.
 */
public interface TestRestService {
    List<TestDTO> getTests(Authentication authentication);
    TestDTO getTest(Long testId);
    TestDTO createTest(TestDTO testDTO, Authentication authentication);
    TestSectionDTO createNextSection(Long testId);
    void answerQuestion(Long questionId, String answer);
    Date finishTest(Long id, Authentication authentication);
}
