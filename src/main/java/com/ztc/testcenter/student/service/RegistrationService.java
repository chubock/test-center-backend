package com.ztc.testcenter.student.service;

import com.ztc.testcenter.config.service.EmailService;
import com.ztc.testcenter.user.domain.ActionCode;
import com.ztc.testcenter.user.domain.Role;
import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.user.service.RoleService;
import com.ztc.testcenter.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Created by yubar on 7/6/17.
 */

@Service
public class RegistrationService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final EmailService emailService;
    private final String appUrl;
    private final Role studentRole;

    @Autowired
    public RegistrationService(PasswordEncoder passwordEncoder, UserService userService, RoleService roleService, EmailService emailService, @Value("${application.url}") String appUrl) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.emailService = emailService;
        this.appUrl = appUrl;
        studentRole = roleService.findRole("STUDENT");
    }

    public User registerStudent(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        ActionCode activationCode = userService.register(user);
        user.setRoles(Collections.singletonList(studentRole));
        emailService.sendMail(user.getUsername(), "Activation Link", "Activation Link: <a href=\"" + appUrl + "/activateUser?code=" + activationCode.getCode() + "\">" + appUrl + "/activateUser?code=" + activationCode.getCode() + "</a>");
        return user;
    }
}
