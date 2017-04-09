package com.ztc.testcenter.controller;

import com.ztc.testcenter.domain.user.ActionCode;
import com.ztc.testcenter.repository.user.ActionCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yubar on 4/9/17.
 */

@Controller
@RequestMapping("/activateUser")
public class ActivationController {

    private final ActionCodeRepository actionCodeRepository;

    @Autowired
    public ActivationController(ActionCodeRepository actionCodeRepository) {
        this.actionCodeRepository = actionCodeRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String activateUser(@RequestParam String code) {
        ActionCode actionCode = actionCodeRepository.findByCode(code);
        if (actionCode == null || actionCode.getExpired()) {
            throw new IllegalArgumentException();
        }

        return "";
    }
}
