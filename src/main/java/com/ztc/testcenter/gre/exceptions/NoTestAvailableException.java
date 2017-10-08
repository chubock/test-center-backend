package com.ztc.testcenter.gre.exceptions;

import com.ztc.testcenter.exceptions.BusinessException;

/**
 * Created by yubar on 6/1/17.
 */
public class NoTestAvailableException extends BusinessException {
    public NoTestAvailableException() {
        super(1105);
    }
}
