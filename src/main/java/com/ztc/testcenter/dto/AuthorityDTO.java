package com.ztc.testcenter.dto;

import com.ztc.testcenter.domain.user.Authority;

/**
 * Created by Yubar on 3/4/2017.
 */

public class AuthorityDTO {

    private Long id;
    private String name;
    private Boolean restricted = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRestricted() {
        return restricted;
    }

    public void setRestricted(Boolean restricted) {
        this.restricted = restricted;
    }

    private void copy(Authority authority) {
        setId(authority.getId());
        setName(authority.getName());
        setRestricted(authority.isRestricted());
    }

    public static AuthorityDTO valueOf(Authority authority) {
        if (authority == null)
            return null;
        AuthorityDTO authorityDTO = new AuthorityDTO();
        authorityDTO.copy(authority);
        return authorityDTO;
    }
}
