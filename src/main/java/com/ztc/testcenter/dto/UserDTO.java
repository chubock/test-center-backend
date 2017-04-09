package com.ztc.testcenter.dto;

import com.ztc.testcenter.domain.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 3/4/2017.
 */
public class UserDTO extends AbstractDTO<User> {

    private Long id;
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private User.Gender gender;
    private Boolean enabled = true;
    private Boolean accountExpired = false;
    private Boolean credentialExpired = false;
    private Boolean locked = false;
    private List<RoleDTO> roles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public User.Gender getGender() {
        return gender;
    }

    public void setGender(User.Gender gender) {
        this.gender = gender;
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

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    @Override
    public User convert(User user) {
        user.setId(getId());
        user.setFirstName(getFirstName());
        user.setLastName(getLastName());
        user.setUsername(getUsername());
        user.setGender(getGender());
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
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setGender(user.getGender());
        userDTO.setAccountExpired(user.getAccountExpired());
        userDTO.setCredentialExpired(user.getCredentialExpired());
        userDTO.setEnabled(user.getEnabled());
        userDTO.setLocked(user.getLocked());
        return userDTO;
    }
}
