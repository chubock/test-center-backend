package com.ztc.testcenter.user.service;

import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yubar on 9/29/17.
 */

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUser(String username) {
        return userRepository.findOne(username);
    }

    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername()))
            throw new IllegalArgumentException();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
