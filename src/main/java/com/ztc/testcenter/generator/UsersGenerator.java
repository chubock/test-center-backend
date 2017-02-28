package com.ztc.testcenter.generator;

import com.ztc.testcenter.domain.User;
import com.ztc.testcenter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * Created by yubar on 2/27/17.
 */

@Service
public class UsersGenerator {

    private final UserRepository userRepository;
    private final Random random = new Random(1);
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public UsersGenerator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void createUser (int number) {
        User user = new User();
        user.setUsername("User " + number);
        user.setAccountExpired(false);
        user.setCredentialExpired(false);
        user.setEnabled(true);
        user.setFirstName("User " + number);
        user.setLastName("User " + number);
        user.setGender(User.Gender.values()[random.nextInt(2)]);
        user.setLocked(false);
        user.setPassword(encoder.encode("User " + number));
        userRepository.save(user);
    }

    public void generateUsers(int count) {
        for (int i=0; i<count; i++)
            createUser(i + 1);
    }
}
