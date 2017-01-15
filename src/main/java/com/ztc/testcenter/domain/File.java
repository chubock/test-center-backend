package com.ztc.testcenter.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Yubar on 1/15/2017.
 */

@Entity
public class File implements Serializable {

    private long id;
    private String name;
    private String contentType;
    private long size;
    private byte[] content;

    public File() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    @Column(length = 1024 * 1024 * 10)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;

        File file = (File) o;

        if (getId() != file.getId()) return false;
        if (getId() > 0)
            return true;
        if (getSize() != file.getSize()) return false;
        if (getName() != null ? !getName().equals(file.getName()) : file.getName() != null) return false;
        if (getContentType() != null ? !getContentType().equals(file.getContentType()) : file.getContentType() != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        if (getId() > 0)
            return (int) getId();
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getContentType() != null ? getContentType().hashCode() : 0);
        result = 31 * result + (int) (getSize() ^ (getSize() >>> 32));
        result = 31 * result + Arrays.hashCode(getContent());
        return result;
    }
}
