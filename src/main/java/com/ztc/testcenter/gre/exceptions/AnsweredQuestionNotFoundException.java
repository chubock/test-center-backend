package com.ztc.testcenter.gre.exceptions;

import com.ztc.testcenter.exceptions.BadRequestException;

/**
 * Created by yubar on 6/1/17.
 */
public class AnsweredQuestionNotFoundException extends BadRequestException {
    public AnsweredQuestionNotFoundException() {
        super(1101);
    }
}
