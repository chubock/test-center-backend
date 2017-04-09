package com.ztc.testcenter.rest;

import com.ztc.testcenter.repository.user.UserRepository;
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
    public List<String> getAuthorities(Authentication authentication) {
        return ((UserDetails) authentication.getPrincipal()).getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toList());
    }
}
