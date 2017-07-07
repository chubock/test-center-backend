package com.ztc.testcenter.student.rest;

import com.ztc.testcenter.student.service.RegistrationService;
import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.user.dto.UserDTO;
import com.ztc.testcenter.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yubar on 3/1/17.
 */

@RestController
@RequestMapping("/student/register")
public class RegistrationRestService {

    private final UserService userService;
    private final RegistrationService registrationService;

    @Autowired
    public RegistrationRestService(UserService userService, RegistrationService registrationService) {
        this.registrationService = registrationService;
        this.userService = userService;
    }

    @RequestMapping(value = "/isUsernameUnique", method = RequestMethod.GET)
    public boolean isUsernameUnique(@RequestParam String username) {
        return userService.isUsernameUnique(username);
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserDTO register(@RequestBody UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getLastName(), userDTO.getFirstName());
        user = registrationService.registerStudent(user);
        return UserDTO.valueOf(user);
    }
}
