package com.ztc.testcenter.gre.domain.question;

/**
 * Created by Yubar on 3/16/2017.
 */

public interface InnerQuestion<T extends Question> {

    Integer getNumber();
    T getParent();


}
