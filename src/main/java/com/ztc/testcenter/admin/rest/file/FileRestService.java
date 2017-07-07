package com.ztc.testcenter.admin.rest.file;

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

@RestController("AdminFileRestService")
@RequestMapping("/admin/files")
public class FileRestService {

    final private FileService fileService;

    @Autowired
    public FileRestService(FileService fileService) {
        this.fileService = fileService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('FILE_REST_SERVICE__UPLOAD_FILE')")
    public Long uploadFile(MultipartHttpServletRequest request) throws IOException {
        MultipartFile file = request.getFile("file");
        if (file != null) {
            File f = new File(file.getOriginalFilename(), file.getContentType(), file.getSize(), file.getBytes());
            f = fileService.save(f);
            return f.getId();
        } else {
            return Long.valueOf(0);
        }
    }
}
