package com.ztc.testcenter.gre.controller.test;

import com.ztc.testcenter.config.security.ApplicationUserDetails;
import com.ztc.testcenter.gre.domain.test.Test;
import com.ztc.testcenter.gre.domain.test.TestSection;
import com.ztc.testcenter.gre.dto.QuestionDTO;
import com.ztc.testcenter.gre.dto.TestDTO;
import com.ztc.testcenter.gre.dto.TestSectionDTO;
import com.ztc.testcenter.gre.service.TestService;
import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yubar on 9/20/17.
 */

@RestController
public class GetCurrentTestController {

    private final TestService testService;

    @Autowired
    public GetCurrentTestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/gre-service/tests/current")
    @PreAuthorize("hasAuthority('STUDENT')")
    public TestDTO handle(Authentication authentication) {
        Test test = testService.getUserCurrentTest(SecurityUtil.getUsername(authentication));
        if (test == null)
            return null;

        TestDTO ret = TestDTO.valueOf(test);
        test.getTemplate()
                .getItems()
                .forEach(testTemplateItem -> ret.getSectionTypes().add(testTemplateItem.getSectionType()));

        test.getTestSections().forEach(testSection -> ret.getTestSections().add(TestSectionDTO.valueOf(testSection)));

        ret.getTestSections()
                .forEach(testSectionDTO -> {
                    TestSection testSection = testService.getTestSection(testSectionDTO.getId());
                    testSection
                            .getAnsweredQuestions()
                            .forEach(answeredQuestion -> testSectionDTO.getAnsweredQuestions().add(QuestionDTO.valueOf(answeredQuestion)));
                });

        testService.startSection(ret.getTestSections().get(ret.getTestSections().size() - 1).getId());

        return ret;
    }
}
