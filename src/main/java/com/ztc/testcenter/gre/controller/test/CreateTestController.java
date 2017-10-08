package com.ztc.testcenter.gre.controller.test;

import com.ztc.testcenter.config.security.ApplicationUserDetails;
import com.ztc.testcenter.gre.domain.test.Test;
import com.ztc.testcenter.gre.dto.QuestionDTO;
import com.ztc.testcenter.gre.dto.TestDTO;
import com.ztc.testcenter.gre.dto.TestSectionDTO;
import com.ztc.testcenter.gre.service.TestService;
import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yubar on 9/20/17.
 */

@RestController
public class CreateTestController {

    private final TestService testService;

    @Autowired
    public CreateTestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/gre-service/tests")
    @PreAuthorize("hasAuthority('STUDENT')")
    public TestDTO handle(@RequestBody TestDTO testDTO, Authentication authentication) {
        Test test = testService.createTest(SecurityUtil.getUsername(authentication), testDTO.getDifficulty());
        TestDTO ret = TestDTO.valueOf(test);
        test.getTemplate().getItems().forEach(testTemplateItem -> ret.getSectionTypes().add(testTemplateItem.getSectionType()));
        test.getTestSections().forEach(testSection -> {
            TestSectionDTO testSectionDTO = TestSectionDTO.valueOf(testSection);
            testSection.getAnsweredQuestions().forEach(answeredQuestion -> testSectionDTO.getAnsweredQuestions().add(QuestionDTO.valueOf(answeredQuestion)));
            ret.getTestSections().add(testSectionDTO);
        });
        return ret;
    }
}
