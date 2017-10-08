package com.ztc.testcenter.gre.exceptions;

import com.ztc.testcenter.exceptions.SystemException;

/**
 * Created by yubar on 6/1/17.
 */
public class NoSectionTemplateAvailableException extends SystemException {
    public NoSectionTemplateAvailableException() {
        super(1104);
    }
}
