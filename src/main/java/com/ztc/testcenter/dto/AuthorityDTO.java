package com.ztc.testcenter.dto;

import com.ztc.testcenter.domain.Authority;
import com.ztc.testcenter.dto.AbstractDTO;

/**
 * Created by Yubar on 3/4/2017.
 */

public class AuthorityDTO extends AbstractDTO<Authority> {

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

    @Override
    public Authority convert() {
        Authority authority = new Authority();
        authority.setId(getId());
        authority.setName(getName());
        authority.setRestricted(getRestricted());
        return authority;
    }
}
