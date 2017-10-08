package com.ztc.testcenter.gre.controller.test;

import com.ztc.testcenter.gre.domain.test.TestSection;
import com.ztc.testcenter.gre.dto.QuestionDTO;
import com.ztc.testcenter.gre.dto.TestSectionDTO;
import com.ztc.testcenter.gre.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yubar on 9/20/17.
 */

@RestController
public class GetTestSectionController {

    private final TestService testService;

    @Autowired
    public GetTestSectionController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("/gre-service/test-sections/{id}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public TestSectionDTO getTestSection(@PathVariable Long id) {
        TestSection testSection = testService.getTestSection(id);
        TestSectionDTO ret = TestSectionDTO.valueOf(testSection);
        testSection
                .getAnsweredQuestions()
                .forEach(answeredQuestion -> ret.getAnsweredQuestions().add(QuestionDTO.valueOf(answeredQuestion)));
        return ret;
    }
}
