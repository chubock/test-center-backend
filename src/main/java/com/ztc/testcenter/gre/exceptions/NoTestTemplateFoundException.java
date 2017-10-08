package com.ztc.testcenter.gre.exceptions;

import com.ztc.testcenter.exceptions.SystemException;

/**
 * Created by yubar on 6/1/17.
 */
public class NoTestTemplateFoundException extends SystemException {
    public NoTestTemplateFoundException() {
        super(1106);
    }
}
