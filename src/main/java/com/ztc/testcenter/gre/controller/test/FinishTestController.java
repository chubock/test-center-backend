package com.ztc.testcenter.gre.controller.test;

import com.ztc.testcenter.gre.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public Response finishTest(@PathVariable Long id, @RequestBody Map<Long, String> answers) {
        return new Response(testService.finishTest(id, answers));
    }

    public class Response {
        private Date finishDate;

        public Response(Date finishDate) {
            this.finishDate = finishDate;
        }
    }
}
