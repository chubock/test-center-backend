package com.ztc.testcenter.question.gre.domain;

/**
 * Created by Yubar on 3/16/2017.
 */

public interface InnerQuestion<T extends Question> {

    Integer getNumber();
    T getParent();


}
