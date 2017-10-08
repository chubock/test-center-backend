package com.ztc.testcenter.gre.exceptions;

import com.ztc.testcenter.exceptions.BusinessException;

/**
 * Created by yubar on 6/1/17.
 */
public class TestTimeFinishedException extends BusinessException {
    public TestTimeFinishedException(Integer code) {
        super(1110);
    }
}
