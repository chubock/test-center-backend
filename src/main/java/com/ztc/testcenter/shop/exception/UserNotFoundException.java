package com.ztc.testcenter.shop.exception;

import com.ztc.testcenter.exceptions.NotFoundException;

/**
 * Created by yubar on 10/1/17.
 */
public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super(1201);
    }
}
