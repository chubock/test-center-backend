package com.ztc.testcenter.security;

import com.ztc.testcenter.domain.user.User;
import com.ztc.testcenter.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yubar on 11/18/2016.
 */

@Service
@Transactional
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user == null)
            throw new UsernameNotFoundException("No User Found for : " + s);
        user.getRoles().size();
        user.getRoles().forEach(role -> role.getAuthorities().size());
        return new ApplicationUserDetails(user);
    }
}
