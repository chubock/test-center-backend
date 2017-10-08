package com.ztc.testcenter.user.dto;

import com.ztc.testcenter.user.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 3/4/2017.
 */
public class UserDTO {

    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private Boolean enabled = true;
    private Boolean accountExpired = false;
    private Boolean credentialExpired = false;
    private Boolean locked = false;
    private List<String> authorities = new ArrayList<>();
    private Integer freeGreTestCount = 1;
    private Integer greTestCount = 0;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
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

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }

    public Integer getFreeGreTestCount() {
        return freeGreTestCount;
    }

    public void setFreeGreTestCount(Integer freeGreTestCount) {
        this.freeGreTestCount = freeGreTestCount;
    }

    public Integer getGreTestCount() {
        return greTestCount;
    }

    public void setGreTestCount(Integer greTestCount) {
        this.greTestCount = greTestCount;
    }

    public User convert(User user) {
        user.setFirstName(getFirstName());
        user.setLastName(getLastName());
        user.setUsername(getUsername());
        user.setAccountExpired(getAccountExpired());
        user.setCredentialExpired(getCredentialExpired());
        user.setEnabled(getEnabled());
        user.setLocked(getLocked());
        return user;
    }

    public static UserDTO valueOf(User user) {
        if (user == null)
            return null;
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setAccountExpired(user.getAccountExpired());
        userDTO.setCredentialExpired(user.getCredentialExpired());
        userDTO.setEnabled(user.getEnabled());
        userDTO.setLocked(user.getLocked());
        return userDTO;
    }
}
