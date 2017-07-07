package com.ztc.testcenter.test.gre.repository;

import com.ztc.testcenter.test.gre.domain.TestResultItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yubar on 7/7/17.
 */
public interface TestResultItemRepository extends JpaRepository<TestResultItem, Long> {
}
