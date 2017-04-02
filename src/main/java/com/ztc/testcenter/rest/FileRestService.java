package com.ztc.testcenter.rest;

import com.ztc.testcenter.domain.File;
import com.ztc.testcenter.dto.FileDTO;
import com.ztc.testcenter.repository.FileRepository;
import com.ztc.testcenter.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
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

    final private FileRepository fileRepository;

    final private ManagerService managerService;

    @Autowired
    public FileRestService(FileRepository fileRepository, ManagerService managerService) {
        this.fileRepository = fileRepository;
        this.managerService = managerService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public void getFile(@PathVariable Long id, HttpServletResponse response) throws IOException {
        File file = fileRepository.findOne(id);
        if (file == null) {
            response.sendError(404);
        } else {
            response.setContentType(file.getContentType());
            response.setContentLengthLong(file.getSize());
            response.setHeader("Content-Disposition", "inline" + "; filename=" + file.getName());
            response.getOutputStream().write(file.getContent());
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public Long uploadFile(MultipartHttpServletRequest request) throws IOException {
        MultipartFile file = request.getFile("file");
        if (file != null) {
            File f = new File(file.getOriginalFilename(), file.getContentType(), file.getSize(), file.getBytes());
            f = managerService.save(f);
            return f.getId();
        } else {
            return Long.valueOf(0);
        }
    }
}
