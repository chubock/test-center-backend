package com.ztc.testcenter.gre.repository.test;

import com.ztc.testcenter.gre.domain.test.TestTemplate;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Yubar on 3/2/2017.
 */

@CacheConfig(cacheNames = "gre-test-templates")
public interface TestTemplateRepository extends JpaRepository<TestTemplate, Long> {

    @Cacheable
    @Override
    @EntityGraph(attributePaths = "items")
    List<TestTemplate> findAll();


}
