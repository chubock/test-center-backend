package com.ztc.testcenter.registration.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by yubar on 9/15/17.
 */

@Entity
public class RegistrationCode {
    private Long id;
    private String phoneNumber;
    private String code;
    private Date createDate;
    private Boolean used;


    protected RegistrationCode() {
    }

    public RegistrationCode(String phoneNumber, String code) {
        setPhoneNumber(phoneNumber);
        setCode(code);
        setCreateDate(new Date());
        setUsed(false);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Size(min = 11, max = 11)
//    @Pattern(regexp = "09^[0-9]{9}")
    @Column(nullable = false, length = 11)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    private void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null)
            throw new NullPointerException();
        this.phoneNumber = phoneNumber;
    }

    @NotNull
    @Column(nullable = false)
    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        if (code == null)
            throw new NullPointerException();
        this.code = code;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDate() {
        return createDate;
    }

    private void setCreateDate(Date createDate) {
        if (createDate == null)
            throw new NullPointerException();
        this.createDate = createDate;
    }

    public Boolean getUsed() {
        return used;
    }

    private void setUsed(Boolean used) {
        if (used == null)
            throw new NullPointerException();
        this.used = used;
    }

    public void use() {
        if (getUsed())
            throw new IllegalStateException();
        setUsed(true);
    }
}
