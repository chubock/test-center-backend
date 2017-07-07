package com.ztc.testcenter.file.repository;

import com.ztc.testcenter.file.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yubar on 1/15/2017.
 */

public interface FileRepository extends JpaRepository<File, Long> {
}
