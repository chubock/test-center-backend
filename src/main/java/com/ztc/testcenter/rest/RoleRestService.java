package com.ztc.testcenter.rest;

import com.ztc.testcenter.domain.user.Role;
import com.ztc.testcenter.dto.AuthorityDTO;
import com.ztc.testcenter.dto.RoleDTO;
import com.ztc.testcenter.repository.user.AuthorityRepository;
import com.ztc.testcenter.repository.user.RoleRepository;
import com.ztc.testcenter.service.ManagerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yubar on 4/4/17.
 */

@RestController
@RequestMapping("/roles")
public class RoleRestService {

    private final RoleRepository repository;
    private final AuthorityRepository authorityRepository;
    private final ManagerService managerService;

    public RoleRestService(RoleRepository repository, AuthorityRepository authorityRepository, ManagerService managerService) {
        this.repository = repository;
        this.authorityRepository = authorityRepository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Page<RoleDTO> getRoles(Pageable pageable) {
        return repository.findAll(pageable).map(RoleDTO::valueOf);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public RoleDTO getRole(@PathVariable Long id) {
        Role role = repository.findOneWithAuthorities(id);
        RoleDTO ret = RoleDTO.valueOf(role);
        role.getAuthorities().forEach(authority -> ret.getAuthorities().add(AuthorityDTO.valueOf(authority)));
        return ret;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public RoleDTO saveRole(@RequestBody RoleDTO roleDTO) {
        Role role;
        if (roleDTO.getId() == null)
            role = new Role(roleDTO.getName());
        else {
            role = repository.getOne(roleDTO.getId());
            role.setName(roleDTO.getName());
            role.getAuthorities().clear();
            Role finalRole = role;
            roleDTO.getAuthorities().forEach(authorityDTO -> finalRole.getAuthorities().add(authorityRepository.getOne(authorityDTO.getId())));
        }
        role = managerService.save(role);
        RoleDTO ret = RoleDTO.valueOf(role);
        role.getAuthorities().forEach(authority -> roleDTO.getAuthorities().add(AuthorityDTO.valueOf(authority)));
        return ret;
    }

}
