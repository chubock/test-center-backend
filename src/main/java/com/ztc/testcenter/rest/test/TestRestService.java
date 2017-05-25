package com.ztc.testcenter.rest.test;

import com.ztc.testcenter.dto.test.TestDTO;
import com.ztc.testcenter.dto.test.TestSectionDTO;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Yubar on 3/4/2017.
 */
public interface TestRestService {
    List<TestDTO> getTests(Authentication authentication);
    TestDTO getTest(Long testId, Authentication authentication);
    TestDTO createTest(TestDTO testDTO, Authentication authentication);
    TestDTO createFreeTest(TestDTO testDTO, Authentication authentication);
    TestSectionDTO getTestSection(Long id, Authentication authentication);
    TestSectionDTO createNextSection(Long testId, Map<Long, String> answers, Authentication authentication);
    void seeQuestion(Long questionId, Authentication authentication);
    void answerQuestion(Long questionId, String answer, Authentication authentication);
    void markQuestion(Long questionId, Authentication authentication);
    void unMarkQuestion(Long questionId, Authentication authentication);
    Date finishTest(Long id, Map<Long, String> answers, Authentication authentication);
}
