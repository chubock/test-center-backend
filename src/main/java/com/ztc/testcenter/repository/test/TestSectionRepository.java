package com.ztc.testcenter.repository.test;

import com.ztc.testcenter.domain.test.Test;
import com.ztc.testcenter.domain.test.TestSection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Yubar on 3/3/2017.
 */
public interface TestSectionRepository extends JpaRepository<TestSection, Long> {

    @Query("select t from TestSection t join fetch t.answeredQuestions where t.id = :id")
    TestSection findOneWithQuestions(@Param("id") Long id);

}
