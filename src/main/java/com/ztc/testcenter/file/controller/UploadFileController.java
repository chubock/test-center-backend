package com.ztc.testcenter.file.controller;

import com.ztc.testcenter.file.domain.File;
import com.ztc.testcenter.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

/**
 * Created by yubar on 9/19/17.
 */

@RestController
public class UploadFileController {

    private final FileService fileService;

    @Autowired
    public UploadFileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/file-service")
    @PreAuthorize("hasAuthority('FILE_UPLOAD')")
    public Long handle(MultipartHttpServletRequest request) throws IOException {
        MultipartFile file = request.getFile("file");
        if (file != null) {
            File f = new File(file.getOriginalFilename(), file.getContentType(), file.getSize(), file.getBytes());
            f = fileService.save(f);
            return f.getId();
        } else {
            return 0L;
        }
    }
}
