package com.ztc.testcenter.gre.domain.test;

import com.ztc.testcenter.user.domain.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by yubar on 10/1/17.
 */

@Entity
public class TestTicket {

    private Long id;
    private String username;
    private Date createDate;
    private Boolean free;
    private Test test;

    protected TestTicket() {
    }

    public TestTicket(String username, Boolean free) {
        setUsername(username);
        setFree(free);
        setCreateDate(new Date());
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
    @Column(nullable = false)
    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        if (username == null)
            throw new NullPointerException();
        this.username = username;
    }

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateDate() {
        return createDate;
    }

    private void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @NotNull
    @Column(nullable = false)
    public Boolean getFree() {
        return free;
    }

    private void setFree(Boolean free) {
        this.free = free;
    }

    @OneToOne(fetch = FetchType.LAZY)
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
