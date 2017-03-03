package com.ztc.testcenter.repository.test;

import com.ztc.testcenter.domain.question.Difficulty;
import com.ztc.testcenter.domain.test.SectionTemplate;
import com.ztc.testcenter.domain.test.SectionType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by yubar on 2/28/17.
 */
public interface SectionTemplateRepository extends JpaRepository<SectionTemplate, Long> {

    @EntityGraph(attributePaths = "items")
    List<SectionTemplate> findBySectionTypeAndDifficulty(SectionType sectionType, Difficulty difficulty);
}
