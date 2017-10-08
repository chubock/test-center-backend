package com.ztc.testcenter.registration.controller;

import com.ztc.testcenter.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yubar on 9/15/17.
 */

@RestController
public class RegisterPhoneNumberController {

    private final RegistrationService registrationService;

    @Autowired
    public RegisterPhoneNumberController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping(value = "/registration-service/register-phone-number/{phoneNumber}")
    public void handle(@PathVariable String phoneNumber) {
        registrationService.registerPhoneNumber(phoneNumber);
    }
}
