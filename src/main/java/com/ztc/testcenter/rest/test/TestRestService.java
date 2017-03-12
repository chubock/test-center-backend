package com.ztc.testcenter.rest.test;

import com.ztc.testcenter.dto.test.TestDTO;
import com.ztc.testcenter.dto.test.TestSectionDTO;

/**
 * Created by Yubar on 3/4/2017.
 */
public interface TestRestService {
    TestDTO createTest();
    TestSectionDTO createNextSection();
    TestSectionDTO getTestSection();
}
