package com.ztc.testcenter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by yubar on 6/1/17.
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public abstract class BadRequestException extends TestCenterException {

    public BadRequestException(Integer code) {
        super(code);
    }
}
