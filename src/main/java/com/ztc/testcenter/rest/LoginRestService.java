package com.ztc.testcenter.rest;

import com.ztc.testcenter.domain.user.User;
import com.ztc.testcenter.dto.AuthorityDTO;
import com.ztc.testcenter.dto.RoleDTO;
import com.ztc.testcenter.dto.UserDTO;
import com.ztc.testcenter.repository.user.UserRepository;
import com.ztc.testcenter.security.ApplicationUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yubar on 3/1/17.
 */

@RestController
@RequestMapping("/login")
public class LoginRestService {

    private final UserRepository userRepository;

    @Autowired
    public LoginRestService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET)
    public UserDTO login(Authentication authentication) {
        User user = ((ApplicationUserDetails) authentication.getPrincipal()).getUser();
        UserDTO ret = UserDTO.valueOf(user);
        user.getRoles().forEach(role -> {
            RoleDTO roleDTO = RoleDTO.valueOf(role);
            role.getAuthorities().forEach(authority -> roleDTO.getAuthorities().add(AuthorityDTO.valueOf(authority)));
            ret.getRoles().add(roleDTO);
        });
        return ret;
    }
}
