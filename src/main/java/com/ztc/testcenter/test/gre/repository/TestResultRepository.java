package com.ztc.testcenter.test.gre.repository;

import com.ztc.testcenter.test.gre.domain.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yubar on 7/7/17.
 */
public interface TestResultRepository extends JpaRepository<TestResult, Long> {
}
