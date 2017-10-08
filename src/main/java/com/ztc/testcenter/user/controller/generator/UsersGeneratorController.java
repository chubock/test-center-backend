package com.ztc.testcenter.user.controller.generator;

import com.ztc.testcenter.user.generator.UsersGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yubar on 9/15/17.
 */

@RestController
public class UsersGeneratorController {

    private final UsersGenerator usersGenerator;

    @Autowired
    public UsersGeneratorController(UsersGenerator usersGenerator) {
        this.usersGenerator = usersGenerator;
    }

    @PostMapping("/user-service/generators/users")
    @PreAuthorize("hasAuthority('USER_MANAGEMENT')")
    public void handle() {
        usersGenerator.generateUsers();
    }
}
