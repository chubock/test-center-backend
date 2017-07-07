package com.ztc.testcenter.file.rest;

import com.ztc.testcenter.file.domain.File;
import com.ztc.testcenter.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Yubar on 1/15/2017.
 */

@RestController
@RequestMapping("/files")
public class FileRestService {

    final private FileService fileService;

    @Autowired
    public FileRestService(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void getFile(@PathVariable Long id, HttpServletResponse response) throws IOException {
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
