package com.ztc.testcenter.dto;

/**
 * Created by Yubar on 1/15/2017.
 */
public class HelloMessageDTO {
    private String message;

    public HelloMessageDTO(String name) {
        this.message = "Hello " + (name == null ? "Unknown" : name);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
