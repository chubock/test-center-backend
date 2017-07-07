package com.ztc.testcenter.user.service;

import com.ztc.testcenter.user.domain.Role;
import com.ztc.testcenter.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yubar on 7/6/17.
 */

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRole(String name) {
        return roleRepository.findByName(name);
    }

    public Role findRoleWithAuthorities(Long id) {
        return roleRepository.findOneWithAuthorities(id);
    }

    public Page<Role> findRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    public Role saveRole(Role role) {
        if (role.getId() == null) {
            return roleRepository.save(role);
        } else {
            Role currentRole = roleRepository.getOne(role.getId());
            currentRole.setName(role.getName());
            currentRole.getAuthorities().clear();
            currentRole.getAuthorities().addAll(role.getAuthorities());
            return currentRole;
        }
    }
}
