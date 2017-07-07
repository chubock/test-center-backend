package com.ztc.testcenter.file.service;

import com.ztc.testcenter.file.domain.File;
import com.ztc.testcenter.file.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yubar on 7/3/17.
 */

@Service
@Transactional
public class FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File find(Long id) {
        return fileRepository.findOne(id);
    }

    public File get(Long id) {
        return fileRepository.getOne(id);
    }

    public File save(File file) {
        return fileRepository.save(file);
    }

}
