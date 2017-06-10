package com.ztc.testcenter.generator;

import com.ztc.testcenter.domain.user.User;
import com.ztc.testcenter.repository.user.RoleRepository;
import com.ztc.testcenter.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yubar on 2/27/17.
 */

@Service
@Transactional
public class UsersGenerator {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public UsersGenerator(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    private void createUser (String username, String role) {
        User user = new User(username, username, username);
        user.setAccountExpired(false);
        user.setCredentialExpired(false);
        user.setEnabled(true);;
        user.setLocked(false);
        user.setPassword(encoder.encode(username));
        user.getRoles().add(roleRepository.findByName(role));
        userRepository.save(user);
    }

    public void generateUsers() {
        createUser("admin", "ADMIN");
        createUser("data", "DATA");
        createUser("sale", "SALE");
        createUser("teacher", "TEACHER");
        createUser("student", "STUDENT");
    }
}
