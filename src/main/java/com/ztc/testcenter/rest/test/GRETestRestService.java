package com.ztc.testcenter.rest.test;

import com.ztc.testcenter.domain.user.User;
import com.ztc.testcenter.domain.test.Test;
import com.ztc.testcenter.domain.test.TestSection;
import com.ztc.testcenter.dto.question.QuestionDTO;
import com.ztc.testcenter.dto.test.TestDTO;
import com.ztc.testcenter.dto.test.TestSectionDTO;
import com.ztc.testcenter.repository.test.TestRepository;
import com.ztc.testcenter.repository.test.TestSectionRepository;
import com.ztc.testcenter.security.ApplicationUserDetails;
import com.ztc.testcenter.service.GRETestService;
import com.ztc.testcenter.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Yubar on 3/9/2017.
 */

@RestController
@RequestMapping("/test/gre")
public class GRETestRestService implements TestRestService {

    private final GRETestService testService;
    private final TestRepository testRepository;
    private final TestSectionRepository testSectionRepository;
    private final QuestionService questionService;

    @Autowired
    public GRETestRestService(GRETestService testService, TestRepository testRepository, TestSectionRepository testSectionRepository, QuestionService questionService) {
        this.testService = testService;
        this.testRepository = testRepository;
        this.testSectionRepository = testSectionRepository;
        this.questionService = questionService;
    }

    private User getUser(Authentication authentication) {
        return ((ApplicationUserDetails) authentication.getPrincipal()).getUser();
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('GRE_TEST_REST_SERVICE__GET_TESTS', 'GRE_TEST_REST_SERVICE__GET_TESTS_ALL')")
    public List<TestDTO> getTests(Authentication authentication) {
        User currentUser = getUser(authentication);
        return testRepository.findByUser(currentUser).stream().map(TestDTO::valueOf).collect(Collectors.toList());
    }

    @Override
    @RequestMapping(value= "{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('GRE_TEST_REST_SERVICE__GET_TEST', 'GRE_TEST_REST_SERVICE__GET_TEST_ALL')")
    public TestDTO getTest(@PathVariable Long id, Authentication authentication) {
        Test test = testRepository.findOneWithSectionsAndTemplate(id);
        TestDTO ret = TestDTO.valueOf(test);
        test.getTemplate().getItems().forEach(testTemplateItem -> ret.getSectionTypes().add(testTemplateItem.getSectionType()));
        test.getTestSections().forEach(testSection -> ret.getTestSections().add(TestSectionDTO.valueOf(testSection)));
        return ret;
    }

    @RequestMapping(value = "current", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('GRE_TEST_REST_SERVICE__GET_CURRENT_TEST')")
    public TestDTO getCurrentTest(Authentication authentication) {
        Long testId = testRepository.findCurrentTestId(getUser(authentication));
        if (testId == null)
            return null;
        TestDTO testDTO = getTest(testId, authentication);
        testDTO.getTestSections().forEach(testSectionDTO -> testSectionDTO.getAnsweredQuestions().addAll(getTestSection(testSectionDTO.getId(), authentication).getAnsweredQuestions()));
        testService.startSection(testDTO.getTestSections().get(testDTO.getTestSections().size() - 1).getId());
        return testDTO;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('GRE_TEST_REST_SERVICE__CREATE_TEST')")
    public TestDTO createTest(@RequestBody TestDTO testDTO, Authentication authentication) {
        User currentUser = getUser(authentication);
        Test test = testService.createTest(currentUser, testDTO.getDifficulty(), testDTO.getIntelligentType());
        return prepareTestResponse(test);
    }

    private TestDTO prepareTestResponse(Test test) {
        TestDTO ret = TestDTO.valueOf(test);
        test.getTemplate().getItems().forEach(testTemplateItem -> ret.getSectionTypes().add(testTemplateItem.getSectionType()));
        test.getTestSections().forEach(testSection -> {
            TestSectionDTO testSectionDTO = TestSectionDTO.valueOf(testSection);
            testSection.getAnsweredQuestions().forEach(answeredQuestion -> testSectionDTO.getAnsweredQuestions().add(QuestionDTO.valueOf(answeredQuestion)));
            ret.getTestSections().add(testSectionDTO);
        });
        return ret;
    }

    @Override
    @RequestMapping(value = "/testSections/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('GRE_TEST_REST_SERVICE__GET_TEST_SECTION', 'GRE_TEST_REST_SERVICE__GET_TEST_SECTION_ALL')")
    public TestSectionDTO getTestSection(@PathVariable Long id, Authentication authentication) {
        TestSection testSection = testSectionRepository.findOneWithQuestions(id);
        TestSectionDTO ret = TestSectionDTO.valueOf(testSection);
        testSection.getAnsweredQuestions().forEach(answeredQuestion -> {
            answeredQuestion.setQuestion(questionService.findQuestion(answeredQuestion.getQuestion().getId(), answeredQuestion.getQuestionType()));
            ret.getAnsweredQuestions().add(QuestionDTO.valueOf(answeredQuestion));
        });
        return ret;
    }

    @Override
    @RequestMapping(value = "{id}/testSections", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('GRE_TEST_REST_SERVICE__CREATE_NEXT_SECTION')")
    public TestSectionDTO createNextSection(@PathVariable Long id, @RequestBody Map<Long, String> answers, Authentication authentication) {
        TestSection testSection = testService.createTestSection(id, answers);
        TestSectionDTO testSectionDTO = TestSectionDTO.valueOf(testSection);
        testSection.getAnsweredQuestions().forEach(answeredQuestion -> testSectionDTO.getAnsweredQuestions().add(QuestionDTO.valueOf(answeredQuestion)));
        return testSectionDTO;
    }

    @Override
    @RequestMapping(value = "question/{id}/see", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_TEST_REST_SERVICE__SEE_QUESTION')")
    public void seeQuestion(@PathVariable Long id, Authentication authentication) {
        testService.seeQuestion(id);
    }

    @Override
    @RequestMapping(value = "question/{id}/answer", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_TEST_REST_SERVICE__ANSWER_QUESTION')")
    public void answerQuestion(@PathVariable("id") Long answeredQuestionId, @RequestBody(required = false) String answer, Authentication authentication) {
        testService.answerQuestion(answeredQuestionId, answer);
    }

    @Override
    @RequestMapping(value = "question/{id}/mark", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_TEST_REST_SERVICE__MARK_QUESTION')")
    public void markQuestion(@PathVariable Long id, Authentication authentication) {
        testService.markQuestion(id);
    }

    @Override
    @RequestMapping(value = "question/{id}/unMark", method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('GRE_TEST_REST_SERVICE__UN_MARK_QUESTION')")
    public void unMarkQuestion(@PathVariable Long id, Authentication authentication) {
        testService.unMarkQuestion(id);
    }

    @Override
    @RequestMapping(value = "/{id}/finish", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('GRE_TEST_REST_SERVICE__FINISH_TEST')")
    public Date finishTest(@PathVariable Long id, @RequestBody Map<Long, String> answers, Authentication authentication) {
        return testService.finishTest(id, answers);
    }
}
