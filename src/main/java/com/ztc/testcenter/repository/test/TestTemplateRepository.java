package com.ztc.testcenter.repository.test;

import com.ztc.testcenter.domain.test.TestTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yubar on 3/2/2017.
 */

public interface TestTemplateRepository extends JpaRepository<TestTemplate, Long> {
}
