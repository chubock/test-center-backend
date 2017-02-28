package com.ztc.testcenter.repository.test;

import com.ztc.testcenter.domain.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yubar on 2/28/17.
 */
public interface TestRepository extends JpaRepository<Test, Long> {

}
