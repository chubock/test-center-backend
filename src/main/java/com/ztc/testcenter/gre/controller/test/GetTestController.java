package com.ztc.testcenter.gre.controller.test;

import com.ztc.testcenter.config.security.ApplicationUserDetails;
import com.ztc.testcenter.gre.domain.test.Test;
import com.ztc.testcenter.gre.dto.TestDTO;
import com.ztc.testcenter.gre.dto.TestSectionDTO;
import com.ztc.testcenter.gre.service.TestService;
import com.ztc.testcenter.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yubar on 9/20/17.
 */

@RestController
public class GetTestController {

    private final TestService testService;

    @Autowired
    public GetTestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/gre-service/tests/{id}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public TestDTO handle(@PathVariable Long id) {
        Test test = testService.getTest(id);
        TestDTO ret = TestDTO.valueOf(test);
        test.getTemplate().getItems().forEach(testTemplateItem -> ret.getSectionTypes().add(testTemplateItem.getSectionType()));
        test.getTestSections().forEach(testSection -> ret.getTestSections().add(TestSectionDTO.valueOf(testSection)));
        return ret;
    }
}
