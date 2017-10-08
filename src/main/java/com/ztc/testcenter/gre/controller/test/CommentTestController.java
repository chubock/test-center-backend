package com.ztc.testcenter.gre.controller.test;

import com.ztc.testcenter.config.security.ApplicationUserDetails;
import com.ztc.testcenter.gre.dto.TestDTO;
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
public class CommentTestController {

    private final TestService testService;

    @Autowired
    public CommentTestController(TestService testService) {
        this.testService = testService;
    }

    @PatchMapping("/gre-service/tests/{id}/comment")
    @PreAuthorize("hasAuthority('STUDENT')")
    public void commentTest(@PathVariable Long id, @RequestBody TestDTO testDTO) {
        testService.commentTest(id, testDTO.getComment());
    }
}
