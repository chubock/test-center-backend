package com.ztc.testcenter.dto;

import com.ztc.testcenter.domain.File;

/**
 * Created by Yubar on 1/15/2017.
 */
public class FileDTO {

    private long id;
    private String name;
    private String contentType;
    private long size;
    private byte[] content;

    public FileDTO() {
    }

    public FileDTO(File file) {
        if (file != null) {
            setId(file.getId());
            setName(file.getName());
            setContentType(file.getContentType());
            setSize(file.getSize());
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

}
