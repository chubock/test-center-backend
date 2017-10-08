package com.ztc.testcenter.gre.exceptions;

import com.ztc.testcenter.exceptions.SystemException;

/**
 * Created by yubar on 6/1/17.
 */
public class NoQuestionAvailableException extends SystemException {
    public NoQuestionAvailableException() {
        super(1103);
    }
}
