package com.ztc.testcenter.user.controller;

import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.user.dto.UserDTO;
import com.ztc.testcenter.user.service.UserService;
import com.ztc.testcenter.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by yubar on 9/30/17.
 */

@RestController
public class GetUserController {

    private final UserService userService;

    @Autowired
    public GetUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-service/user")
    @PreAuthorize("isAuthenticated()")
    public UserDTO handle(Authentication authentication) {

        User user = userService.getUser(SecurityUtil.getUsername(authentication));
        UserDTO ret = UserDTO.valueOf(user);
        authentication.getAuthorities().forEach(authority -> ret.getAuthorities().add(authority.getAuthority()));
        return ret;

    }
}
