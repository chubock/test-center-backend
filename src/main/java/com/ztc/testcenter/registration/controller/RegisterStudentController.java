package com.ztc.testcenter.registration.controller;

import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yubar on 9/15/17.
 */

@RestController
public class RegisterStudentController {

    private final RegistrationService registrationService;

    @Autowired
    public RegisterStudentController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration-service/register-student")
    public void handle(@RequestBody Request request) {
        User user = new User(request.getPhoneNumber(), request.getLastName(), request.getFirstName());
        user.setPassword(request.getPassword());
        registrationService.registerUser(user, request.getRegistrationCode());
    }

    private static class Request {
        private String phoneNumber;
        private String firstName;
        private String lastName;
        private String password;
        private String registrationCode;

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRegistrationCode() {
            return registrationCode;
        }

        public void setRegistrationCode(String registrationCode) {
            this.registrationCode = registrationCode;
        }
    }
}
