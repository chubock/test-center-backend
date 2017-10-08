package com.ztc.testcenter.gre.exceptions;

import com.ztc.testcenter.exceptions.BadRequestException;

/**
 * Created by yubar on 6/1/17.
 */
public class UserNotFoundException extends BadRequestException {
    public UserNotFoundException() {
        super(1111);
    }
}
