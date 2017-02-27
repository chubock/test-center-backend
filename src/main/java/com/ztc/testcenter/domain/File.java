package com.ztc.testcenter.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

/**
 * Created by Yubar on 1/15/2017.
 */

@Entity
public class File implements Serializable {

    private Long id;
    private String name;
    private String contentType;
    private Long size;
    private byte[] content;

    public File() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @NotNull
    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    @NotNull
    @Column(length = 1024 * 1024 * 100)
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(id, file.id) &&
                Objects.equals(name, file.name) &&
                Objects.equals(contentType, file.contentType) &&
                Objects.equals(size, file.size) &&
                Arrays.equals(content, file.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contentType, size, content);
    }
}
