package com.ztc.testcenter.repository;

import com.ztc.testcenter.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yubar on 1/15/2017.
 */

public interface FileRepository extends JpaRepository<File, Long> {
}
