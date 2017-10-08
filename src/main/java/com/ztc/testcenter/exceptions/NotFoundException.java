package com.ztc.testcenter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by yubar on 6/1/17.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public abstract class NotFoundException extends TestCenterException {

    public NotFoundException(Integer code) {
        super(code);
    }
}
