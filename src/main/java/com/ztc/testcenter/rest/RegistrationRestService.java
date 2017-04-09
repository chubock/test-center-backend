package com.ztc.testcenter.rest;

import com.ztc.testcenter.domain.user.User;
import com.ztc.testcenter.dto.UserDTO;
import com.ztc.testcenter.repository.user.UserRepository;
import com.ztc.testcenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yubar on 3/1/17.
 */

@RestController
@RequestMapping("/register")
public class RegistrationRestService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @Autowired
    public RegistrationRestService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @RequestMapping(value = "/isUsernameUnique", method = RequestMethod.GET)
    public boolean isUsernameUnique(@RequestParam String username) {
        return userRepository.findByUsername(username) == null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserDTO register(@RequestBody UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getLastName(), userDTO.getFirstName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user = userService.register(user).getUser();
        return UserDTO.valueOf(user);
    }
}
