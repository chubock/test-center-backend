package com.ztc.testcenter.gre.controller.test;

import com.ztc.testcenter.config.security.ApplicationUserDetails;
import com.ztc.testcenter.gre.domain.test.Test;
import com.ztc.testcenter.gre.dto.QuestionDTO;
import com.ztc.testcenter.gre.dto.TestDTO;
import com.ztc.testcenter.gre.dto.TestSectionDTO;
import com.ztc.testcenter.gre.service.TestService;
import com.ztc.testcenter.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * Created by yubar on 9/20/17.
 */

@RestController
public class FinishTestController {

    private final TestService testService;

    @Autowired
    public FinishTestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/gre-service/tests/{id}/finish")
    @PreAuthorize("hasAuthority('STUDENT')")
    public Date finishTest(@PathVariable Long id, @RequestBody Map<Long, String> answers) {
        return testService.finishTest(id, answers);
    }
}
