package com.ztc.testcenter.exceptions;

/**
 * Created by yubar on 6/1/17.
 */
public class BusinessException extends TestCenterException {
    public BusinessException(Integer code) {
        super(code);
    }
}
