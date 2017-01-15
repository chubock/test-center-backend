package com.ztc.testcenter.rest;

import com.ztc.testcenter.dto.HelloMessageDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Yubar on 1/15/2017.
 */

@RestController
@RequestMapping("/sayHello")
public class HelloRestService {

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public HelloMessageDTO sayHello(@PathVariable String name) {
        return new HelloMessageDTO(name);
    }
}
