package com.ztc.testcenter.service;

import com.ztc.testcenter.domain.User;
import com.ztc.testcenter.domain.question.Difficulty;
import com.ztc.testcenter.domain.test.Test;
import com.ztc.testcenter.domain.test.TestSection;

import java.util.Date;

/**
 * Created by Yubar on 3/9/2017.
 */
public interface TestService {
    Test createTest(User user, Difficulty difficulty, Test.TestIntelligentType intelligentType);
    TestSection createTestSection(Long testId);
    void answerQuestion(Long answeredQuestionId, String answer);
    Date finishTest(Long testId);
}