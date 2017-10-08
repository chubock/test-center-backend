package com.ztc.testcenter.registration.controller;

import com.ztc.testcenter.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yubar on 9/15/17.
 */

@RestController
public class VerifyPhoneNumberController {

    private final RegistrationService registrationService;

    @Autowired
    public VerifyPhoneNumberController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration-service/verify-phone-number/{phoneNumber}/{verificationCode}")
    public Response handle(@PathVariable String phoneNumber, @PathVariable String verificationCode) {
        String registrationCode = registrationService.verifyPhoneNumber(phoneNumber, verificationCode);
        return new Response(registrationCode);
    }

    public static class Response {
        private String registrationCode;

        Response(String registrationCode) {
            this.registrationCode = registrationCode;
        }

        public String getRegistrationCode() {
            return registrationCode;
        }
    }
}
