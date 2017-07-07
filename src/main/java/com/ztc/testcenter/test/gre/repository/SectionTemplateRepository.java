package com.ztc.testcenter.test.gre.repository;

import com.ztc.testcenter.question.gre.domain.Difficulty;
import com.ztc.testcenter.test.gre.domain.SectionTemplate;
import com.ztc.testcenter.test.gre.domain.SectionType;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yubar on 2/28/17.
 */

@CacheConfig(cacheNames = "section_templates")
public interface SectionTemplateRepository extends JpaRepository<SectionTemplate, Long> {

    @Cacheable
    @EntityGraph(attributePaths = "items")
    List<SectionTemplate> findBySectionTypeAndDifficultyAndFree(SectionType sectionType, Difficulty difficulty, Boolean free);
}
