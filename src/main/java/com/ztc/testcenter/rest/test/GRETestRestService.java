package com.ztc.testcenter.rest.test;

import com.ztc.testcenter.domain.User;
import com.ztc.testcenter.domain.test.Test;
import com.ztc.testcenter.domain.test.TestSection;
import com.ztc.testcenter.dto.question.QuestionDTO;
import com.ztc.testcenter.dto.test.TestDTO;
import com.ztc.testcenter.dto.test.TestSectionDTO;
import com.ztc.testcenter.repository.UserRepository;
import com.ztc.testcenter.repository.test.TestRepository;
import com.ztc.testcenter.service.GRETestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Yubar on 3/9/2017.
 */

@RestController
@RequestMapping("/test/gre")
public class GRETestRestService {

    private final GRETestService testService;
    private final TestRepository testRepository;
    private final UserRepository userRepository;

    @Autowired
    public GRETestRestService(GRETestService testService, TestRepository testRepository, UserRepository userRepository) {
        this.testService = testService;
        this.testRepository = testRepository;
        this.userRepository = userRepository;
    }

    private User getUser() {
        return userRepository.getOne(10l);
    }

    @RequestMapping(method = RequestMethod.POST)
    public TestDTO createTest() {
        Test test = testService.createTest(getUser());
        return null;
    }

    @RequestMapping(value = "{id}/testSections", method = RequestMethod.POST)
    public TestSectionDTO createNextSection(@PathVariable Long id) {
        Test test = testRepository.getOne(id);
        TestSection testSection = testService.createTestSection(test);
        TestSectionDTO testSectionDTO = TestSectionDTO.valueOf(testSection);
        testSection.getAnsweredQuestions().forEach(answeredQuestion -> {
            QuestionDTO.valueOf(answeredQuestion.getQuestion());
        });
        return null;
    }
}
