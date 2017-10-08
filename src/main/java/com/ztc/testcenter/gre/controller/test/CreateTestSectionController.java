package com.ztc.testcenter.gre.controller.test;

import com.ztc.testcenter.config.security.ApplicationUserDetails;
import com.ztc.testcenter.gre.domain.test.Test;
import com.ztc.testcenter.gre.domain.test.TestSection;
import com.ztc.testcenter.gre.dto.QuestionDTO;
import com.ztc.testcenter.gre.dto.TestDTO;
import com.ztc.testcenter.gre.dto.TestSectionDTO;
import com.ztc.testcenter.gre.service.TestService;
import com.ztc.testcenter.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by yubar on 9/20/17.
 */

@RestController
public class CreateTestSectionController {

    private final TestService testService;

    @Autowired
    public CreateTestSectionController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/gre-service/tests/{id}/test-sections")
    @PreAuthorize("hasAuthority('STUDENT')")
    public TestSectionDTO handle(@PathVariable Long id, @RequestBody Map<Long, String> answers) {
        TestSection testSection = testService.createTestSection(id, answers);
        TestSectionDTO testSectionDTO = TestSectionDTO.valueOf(testSection);
        testSection.getAnsweredQuestions()
                .forEach(answeredQuestion ->
                        testSectionDTO.getAnsweredQuestions().add(QuestionDTO.valueOf(answeredQuestion))
                );
        return testSectionDTO;
    }
}
