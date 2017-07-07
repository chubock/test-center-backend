package com.ztc.testcenter.student.rest.gre;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yubar on 7/7/17.
 */

@RestController
@RequestMapping("/student/tests/gre/{id}/results/")
public class TestResultRestService {

    @RequestMapping("correct-questions")
    public void testCorrectQuestions(@PathVariable Long id) {

    }
}
