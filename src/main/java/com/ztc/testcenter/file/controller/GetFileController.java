package com.ztc.testcenter.file.controller;

import com.ztc.testcenter.file.domain.File;
import com.ztc.testcenter.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yubar on 9/19/17.
 */

@RestController
public class GetFileController {

    private final FileService fileService;

    @Autowired
    public GetFileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/file-service/{id}")
    public void handle(@PathVariable Long id, HttpServletResponse response) throws IOException {
        File file = fileService.find(id);
        if (file == null) {
            response.sendError(404);
        } else {
            response.setContentType(file.getContentType());
            response.setContentLengthLong(file.getSize());
            response.setHeader("Content-Disposition", "inline" + "; filename=" + file.getName());
            response.getOutputStream().write(file.getContent());
        }
    }
}
