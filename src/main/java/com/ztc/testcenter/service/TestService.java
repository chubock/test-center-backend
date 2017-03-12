package com.ztc.testcenter.service;

import com.ztc.testcenter.domain.User;
import com.ztc.testcenter.domain.test.Test;
import com.ztc.testcenter.domain.test.TestSection;

/**
 * Created by Yubar on 3/9/2017.
 */
public interface TestService {
    Test createTest(User user);
    TestSection createTestSection(Test test);
}
