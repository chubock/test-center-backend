package com.ztc.testcenter.gre.repository.test;

import com.ztc.testcenter.gre.domain.test.TestSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Yubar on 3/3/2017.
 */
public interface TestSectionRepository extends JpaRepository<TestSection, Long> {

    @Query("select t from TestSection t join fetch t.answeredQuestions where t.id = :id")
    TestSection findOneWithQuestions(@Param("id") Long id);

}
