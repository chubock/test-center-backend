package com.ztc.testcenter.file.dto;

import com.ztc.testcenter.file.domain.File;

/**
 * Created by Yubar on 1/15/2017.
 */
public class FileDTO {

    private Long id;
    private String name;
    private String contentType;
    private Long size;
    private byte[] content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public File convert(File file) {
        file.setId(getId());
        file.setContentType(getContentType());
        file.setName(getName());
        file.setSize(getSize());
        file.setContent(getContent());
        return file;
    }

    void copy(File file) {
        setId(file.getId());
        setName(file.getName());
        setContentType(file.getContentType());
        setSize(file.getSize());
    }

    public static FileDTO valueOf(File file) {
        if (file == null)
            return null;
        FileDTO fileDTO = new FileDTO();
        fileDTO.copy(file);
        return fileDTO;
    }

}
