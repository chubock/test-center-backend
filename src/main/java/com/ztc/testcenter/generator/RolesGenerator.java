package com.ztc.testcenter.generator;

import com.ztc.testcenter.domain.user.Role;
import com.ztc.testcenter.repository.user.AuthorityRepository;
import com.ztc.testcenter.repository.user.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yubar on 4/3/17.
 */

@Service
public class RolesGenerator {

    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;

    public RolesGenerator(RoleRepository roleRepository, AuthorityRepository authorityRepository) {
        this.roleRepository = roleRepository;
        this.authorityRepository = authorityRepository;
    }

    @Transactional
    public void createAdminRole() {
        Role role = new Role("ADMIN");
        role.getAuthorities().addAll(authorityRepository.findAll());
        roleRepository.save(role);
    }

    @Transactional
    public void createUserRole() {
        Role role = new Role("USER");
        role.getAuthorities().addAll(authorityRepository.findAll().subList(0, 10));
        roleRepository.save(role);
    }
}
