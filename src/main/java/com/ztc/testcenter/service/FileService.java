package com.ztc.testcenter.service;

import com.ztc.testcenter.domain.File;
import com.ztc.testcenter.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yubar on 1/15/2017.
 */

@Service
@Transactional
public class FileService {

    @Autowired
    FileRepository fileRepository;

    public File saveFile(File file) {
        return fileRepository.save(file);
    }
}
