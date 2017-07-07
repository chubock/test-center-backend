package com.ztc.testcenter.user.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Yubar on 4/8/2017.
 */

@Entity
@Table(name = "ACTION_CODES")
public class ActionCode implements Serializable {

    private Long id;
    private String code;
    private User user;
    private Date date = new Date();
    private Action action;
    private Boolean expired = false;

    protected ActionCode() {
    }

    public ActionCode(User user, Action action) {
        setUser(user);
        setAction(action);
        setCode(UUID.randomUUID().toString());
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
    @ManyToOne(optional = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        if (user == null)
            throw new NullPointerException();
        this.user = user;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    public Date getDate() {
        return date;
    }

    private void setDate(Date date) {
        this.date = date;
    }

    @NotNull
    @Enumerated
    @Column(nullable = false)
    public Action getAction() {
        return action;
    }

    private void setAction(Action action) {
        this.action = action;
    }

    public Boolean getExpired() {
        return expired;
    }

    private void setExpired(Boolean expired) {
        if (expired == null)
            throw new NullPointerException();
        this.expired = expired;
    }

    public void expire() {
        if (expired)
            throw new IllegalStateException();
        setExpired(true);
    }

    public enum Action {
        ACTIVATE_USER,
        CHANGE_PASSWORD
    }
}
