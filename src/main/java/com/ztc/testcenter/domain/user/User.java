package com.ztc.testcenter.domain.user;

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

    private Long id;
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private Gender gender = Gender.MALE;
    private Boolean enabled = true;
    private Boolean accountExpired = false;
    private Boolean credentialExpired = false;
    private Boolean locked = false;
    private List<Role> roles = new ArrayList<>();
    private Integer freeGreTestCount = 1;
    private Integer greTestCount = 0;

    protected User() {
    }

    public User(String username, String lastName, String firstName) {
        setUsername(username);
        setLastName(lastName);
        setFirstName(firstName);
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
    @Column(nullable = false, unique = true)
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

    @NotNull
    @Enumerated
    @Column(nullable = false)
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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

    @ManyToMany
    @JoinTable(name = "USER_ROLES")
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @NotNull
    @Column(nullable = false)
    public Integer getFreeGreTestCount() {
        return freeGreTestCount;
    }

    private void setFreeGreTestCount(Integer freeGRETestCount) {
        if (freeGRETestCount == null)
            throw new NullPointerException();
        if (freeGRETestCount < 0)
            throw new IllegalArgumentException();
        this.freeGreTestCount = freeGRETestCount;
    }

    public void decrementFreeGRETestCount() {
        if (freeGreTestCount < 1)
            throw new IllegalStateException();
        freeGreTestCount--;
    }

    public void increaseFreeGRETestCount(int increase) {
        freeGreTestCount +=increase;
    }

    @NotNull
    @Column(nullable = false)
    public Integer getGreTestCount() {
        return greTestCount;
    }

    private void setGreTestCount(Integer greTestCount) {
        if (greTestCount == null)
            throw new NullPointerException();
        if (greTestCount < 0)
            throw new IllegalArgumentException();
        this.greTestCount = greTestCount;
    }

    public void decrementGreTestCount() {
        if (greTestCount < 1)
            throw new IllegalStateException();
        greTestCount--;
    }

    public void increaseGreTestCount(int increase) {
        greTestCount+=increase;
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

    public enum Gender {
        MALE,
        FEMALE
    }

}
