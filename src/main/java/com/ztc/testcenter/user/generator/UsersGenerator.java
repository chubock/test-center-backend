package com.ztc.testcenter.user.generator;

import com.ztc.testcenter.user.domain.Authority;
import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * Created by yubar on 2/27/17.
 */

@Service
@Transactional
public class UsersGenerator {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public UsersGenerator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void createUser (String username, Authority... authorities) {
        User user = new User(username, username, username);
        user.setAccountExpired(false);
        user.setCredentialExpired(false);
        user.setEnabled(true);;
        user.setLocked(false);
        user.setPassword(encoder.encode(username));
        user.setAuthorities(Arrays.asList(authorities));
        userRepository.save(user);
    }

    public void generateUsers() {
        createUser("admin", Authority.GRE_CONTENT, Authority.GRE_TEACHER, Authority.SALE, Authority.USER_MANAGEMENT, Authority.FILE_UPLOAD);
        createUser("data", Authority.GRE_CONTENT, Authority.FILE_UPLOAD);
        createUser("sale", Authority.SALE);
        createUser("teacher", Authority.GRE_TEACHER, Authority.FILE_UPLOAD);
        createUser("student", Authority.STUDENT);
    }
}
