package com.ztc.testcenter.user.service;

import com.ztc.testcenter.user.domain.ActionCode;
import com.ztc.testcenter.user.domain.Role;
import com.ztc.testcenter.user.domain.User;
import com.ztc.testcenter.user.repository.ActionCodeRepository;
import com.ztc.testcenter.user.repository.RoleRepository;
import com.ztc.testcenter.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yubar on 4/5/17.
 */

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final ActionCodeRepository actionCodeRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, ActionCodeRepository actionCodeRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.actionCodeRepository = actionCodeRepository;
        this.roleRepository = roleRepository;
    }

    public boolean isUsernameUnique(String username) {
        User user = userRepository.findByUsername(username);
        return user == null;
    }

    public ActionCode register(User user) {
        user.setEnabled(false);
        user = userRepository.save(user);
        ActionCode actionCode = new ActionCode(user, ActionCode.Action.ACTIVATE_USER);
        actionCodeRepository.save(actionCode);
        return actionCode;
    }

    public void activateUser(String code) {
        ActionCode actionCode = actionCodeRepository.findByCode(code);
        if (actionCode == null || actionCode.getExpired() || actionCode.getAction() != ActionCode.Action.ACTIVATE_USER)
            throw new IllegalArgumentException();
        actionCode.getUser().setEnabled(true);
        actionCode.expire();
        userRepository.save(actionCode.getUser());
    }
}
