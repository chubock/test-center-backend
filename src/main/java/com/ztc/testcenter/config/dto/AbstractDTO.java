package com.ztc.testcenter.config.dto;

/**
 * Created by Yubar on 1/19/2017.
 */
public abstract class AbstractDTO<T> {
    public abstract T convert(T t);
}
