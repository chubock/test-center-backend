package com.ztc.testcenter.dto;

import com.ztc.testcenter.domain.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yubar on 3/4/2017.
 */

public class RoleDTO extends AbstractDTO<Role> {

    private Long id;
    private String name;
    private List<AuthorityDTO> authorities = new ArrayList<>();

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

    public List<AuthorityDTO> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityDTO> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Role convert() {
        Role role = new Role();
        role.setId(getId());
        role.setName(getName());
        getAuthorities().forEach(authorityDTO -> role.getAuthorities().add(authorityDTO.convert()));
        return role;
    }

    public static RoleDTO valueOf(Role role) {
        if (role == null)
            return null;
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        return roleDTO;
    }
}
