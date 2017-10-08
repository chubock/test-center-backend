package com.ztc.testcenter.registration.exceptions;

import com.ztc.testcenter.exceptions.BadRequestException;

/**
 * Created by yubar on 9/30/17.
 */
public class RegistrationCodeNotValidException extends BadRequestException {
    public RegistrationCodeNotValidException() {
        super(1203);
    }
}
