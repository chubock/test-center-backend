package com.ztc.testcenter.gre.exceptions;

import com.ztc.testcenter.exceptions.BusinessException;

/**
 * Created by yubar on 6/1/17.
 */
public class NoFreeTestAvailableException extends BusinessException {
    public NoFreeTestAvailableException() {
        super(1102);
    }
}
