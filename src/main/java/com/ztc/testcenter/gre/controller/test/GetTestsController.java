package com.ztc.testcenter.gre.controller.test;

import com.ztc.testcenter.config.security.ApplicationUserDetails;
import com.ztc.testcenter.gre.dto.TestDTO;
import com.ztc.testcenter.gre.service.TestService;
import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yubar on 9/20/17.
 */

@RestController
public class GetTestsController {

    private final TestService testService;

    @Autowired
    public GetTestsController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/gre-service/tests")
    @PreAuthorize("hasAuthority('STUDENT')")
    public List<TestDTO> handle(Authentication authentication) {
        return testService
                .getUserTests(SecurityUtil.getUsername(authentication))
                .stream()
                .map(TestDTO::valueOf)
                .collect(Collectors.toList());
    }
}
