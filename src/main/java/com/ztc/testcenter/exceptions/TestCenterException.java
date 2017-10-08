package com.ztc.testcenter.exceptions;

/**
 * Created by yubar on 9/30/17.
 */
public abstract class TestCenterException extends RuntimeException {

    private final Integer code;

    public TestCenterException(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
