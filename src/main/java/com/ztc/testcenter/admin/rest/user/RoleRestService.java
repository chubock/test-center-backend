package com.ztc.testcenter.admin.rest.user;

import com.ztc.testcenter.user.domain.Role;
import com.ztc.testcenter.user.dto.RoleDTO;
import com.ztc.testcenter.user.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yubar on 4/4/17.
 */

@RestController
@RequestMapping("/admin/roles")
public class RoleRestService {

    private final RoleService roleService;

    public RoleRestService(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_REST_SERVICE__GET_ROLES')")
    public Page<RoleDTO> getRoles(Pageable pageable) {
        return roleService.findRoles(pageable).map(RoleDTO::valueOf);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ROLE_REST_SERVICE__GET_ROLE')")
    public RoleDTO getRole(@PathVariable Long id) {
        Role role = roleService.findRoleWithAuthorities(id);
        RoleDTO ret = RoleDTO.valueOf(role);
        ret.getAuthorities().addAll(role.getAuthorities());
        return ret;
    }

    @RequestMapping(method = RequestMethod.PUT)
    @PreAuthorize("hasAuthority('ROLE_REST_SERVICE__SAVE_ROLE')")
    public RoleDTO saveRole(@RequestBody RoleDTO roleDTO) {
        Role role = new Role(roleDTO.getName());
        role.setAuthorities(roleDTO.getAuthorities());
        role.setId(roleDTO.getId());
        role = roleService.saveRole(role);
        RoleDTO ret = RoleDTO.valueOf(role);
        roleDTO.getAuthorities().addAll(role.getAuthorities());
        return ret;
    }

}
