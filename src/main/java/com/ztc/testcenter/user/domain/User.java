package com.ztc.testcenter.user.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 2/10/2017.
 */

@Entity
@Table(name = "users")
public class User implements Serializable {

    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private Boolean enabled = true;
    private Boolean accountExpired = false;
    private Boolean credentialExpired = false;
    private Boolean locked = false;
    private List<Authority> authorities = new ArrayList<>();

    protected User() {
    }

    public User(String username, String lastName, String firstName) {
        setUsername(username);
        setLastName(lastName);
        setFirstName(firstName);
    }

    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null)
            throw new NullPointerException();
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null)
            throw new NullPointerException();
        this.lastName = lastName;
    }

    @NotNull
    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null)
            throw new NullPointerException();
        this.firstName = firstName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(Boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public Boolean getCredentialExpired() {
        return credentialExpired;
    }

    public void setCredentialExpired(Boolean credentialExpired) {
        this.credentialExpired = credentialExpired;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    @ElementCollection
    @Enumerated(EnumType.STRING)
    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> roles) {
        this.authorities = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return getUsername() != null ? getUsername().equals(user.getUsername()) : user.getUsername() == null;
    }

    @Override
    public int hashCode() {
        return getUsername() != null ? getUsername().hashCode() : 0;
    }

}
