package com.ztc.testcenter.gre.exceptions;

import com.ztc.testcenter.exceptions.BadRequestException;

/**
 * Created by yubar on 6/1/17.
 */
public class TestNotFoundException extends BadRequestException {
    public TestNotFoundException() {
        super(1107);
    }
}
