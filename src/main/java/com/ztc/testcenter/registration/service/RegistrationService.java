package com.ztc.testcenter.registration.service;

import com.ztc.testcenter.gre.service.TestTicketService;
import com.ztc.testcenter.registration.domain.VerificationCode;
import com.ztc.testcenter.registration.exceptions.RegistrationCodeNotValidException;
import com.ztc.testcenter.registration.exceptions.VerificationCodeNotValidException;
import com.ztc.testcenter.registration.exceptions.PhoneNumberNotValidException;
import com.ztc.testcenter.registration.repository.RegistrationCodeRepository;
import com.ztc.testcenter.user.domain.Authority;
import com.ztc.testcenter.registration.domain.RegistrationCode;
import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.registration.repository.VerificationCodeRepository;
import com.ztc.testcenter.user.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.Date;

/**
 * Created by yubar on 7/6/17.
 */

@Service
@Transactional
public class RegistrationService {

    private final SecureRandom secureRandom = new SecureRandom();
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final VerificationCodeRepository verificationCodeRepository;
    private final RegistrationCodeRepository registrationCodeRepository;
    private final UserService userService;
    private final Logger logger;
    private final TestTicketService testTicketService;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    public RegistrationService(
            VerificationCodeRepository verificationCodeRepository,
            RegistrationCodeRepository registrationCodeRepository,
            UserService userService,
            Logger logger, TestTicketService testTicketService) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.registrationCodeRepository = registrationCodeRepository;
        this.userService = userService;
        this.logger = logger;
        this.testTicketService = testTicketService;
    }

    public void registerPhoneNumber(String phoneNumber) {

        if (registrationCodeRepository.existsByPhoneNumberAndUsedIsTrue(phoneNumber))
            throw new PhoneNumberNotValidException();

        VerificationCode verificationCode =
                verificationCodeRepository.findByPhoneNumberAndExpireDateAfter(phoneNumber, new Date());

        if (verificationCode == null) {
            verificationCode = new VerificationCode(phoneNumber, String.valueOf(secureRandom.nextInt(999999)), 180l);
            verificationCode = verificationCodeRepository.save(verificationCode);
        }

        logger.info("Sending PhoneNumberRegisteredMessage (phoneNumber = " + phoneNumber + ", verificationCode = " + verificationCode.getCode());
    }

    public String verifyPhoneNumber(String phoneNumber, String code) {

        VerificationCode verificationCode =
                verificationCodeRepository.findFirstByPhoneNumberAndCodeOrderByCreateDateDesc(phoneNumber, code);

        if (verificationCode == null || verificationCode.getExpireDate().before(new Date()))
            throw new VerificationCodeNotValidException();

        RegistrationCode registrationCode = registrationCodeRepository.findByPhoneNumber(phoneNumber);

        if (registrationCode == null)
            registrationCode = new RegistrationCode(phoneNumber, String.valueOf(secureRandom.nextInt(999999)));

        if (registrationCode.getUsed())
            throw new PhoneNumberNotValidException();

        registrationCodeRepository.save(registrationCode);

        return registrationCode.getCode();
    }

    public void registerUser(User user, String registrationCode) {

        RegistrationCode r = registrationCodeRepository.findByPhoneNumber(user.getUsername());
        if (r == null || !r.getCode().equals(registrationCode))
            throw new RegistrationCodeNotValidException();

        r.use();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(Collections.singletonList(Authority.STUDENT));

        user = userService.createUser(user);

        testTicketService.createTestTicketForUser(user.getUsername(), true, 1);
        testTicketService.createTestTicketForUser(user.getUsername(), false, 3);

    }
}
