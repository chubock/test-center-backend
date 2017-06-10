package com.ztc.testcenter.rest;

import com.ztc.testcenter.domain.user.Role;
import com.ztc.testcenter.dto.RoleDTO;
import com.ztc.testcenter.repository.user.RoleRepository;
import com.ztc.testcenter.service.ManagerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yubar on 4/4/17.
 */

@RestController
@RequestMapping("/roles")
public class RoleRestService {

    private final RoleRepository repository;
    private final ManagerService managerService;

    public RoleRestService(RoleRepository repository, ManagerService managerService) {
        this.repository = repository;
        this.managerService = managerService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_REST_SERVICE__GET_ROLES')")
    public Page<RoleDTO> getRoles(Pageable pageable) {
        return repository.findAll(pageable).map(RoleDTO::valueOf);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_REST_SERVICE__GET_ROLE')")
    public RoleDTO getRole(@PathVariable Long id) {
        Role role = repository.findOneWithAuthorities(id);
        RoleDTO ret = RoleDTO.valueOf(role);
        role.getAuthorities().size();
        ret.getAuthorities().addAll(role.getAuthorities());
        return ret;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ROLE_REST_SERVICE__SAVE_ROLE')")
    public RoleDTO saveRole(@RequestBody RoleDTO roleDTO) {
        Role role;
        if (roleDTO.getId() == null)
            role = new Role(roleDTO.getName());
        else {
            role = repository.getOne(roleDTO.getId());
            role.setName(roleDTO.getName());
            role.getAuthorities().clear();
            role.getAuthorities().size();
            role.getAuthorities().addAll(roleDTO.getAuthorities());
        }
        role = managerService.save(role);
        RoleDTO ret = RoleDTO.valueOf(role);
        role.getAuthorities().size();
        roleDTO.getAuthorities().addAll(role.getAuthorities());
        return ret;
    }

}
