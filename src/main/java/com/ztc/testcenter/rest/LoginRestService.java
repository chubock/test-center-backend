package com.ztc.testcenter.rest;

import com.ztc.testcenter.domain.user.User;
import com.ztc.testcenter.dto.UserDTO;
import com.ztc.testcenter.repository.user.UserRepository;
import com.ztc.testcenter.security.ApplicationUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by yubar on 3/1/17.
 */

@RestController
@RequestMapping("/login")
public class LoginRestService {

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET)
    public UserDTO login(Authentication authentication) {
        User user = ((ApplicationUserDetails) authentication.getPrincipal()).getUser();
        UserDTO ret = UserDTO.valueOf(user);
        user.getRoles().forEach(role -> ret.getRoles().add(role.getName()));
        return ret;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
