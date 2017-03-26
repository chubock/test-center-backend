package com.ztc.testcenter.security;

import com.ztc.testcenter.domain.*;
import com.ztc.testcenter.repository.AuthorityRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Yubar on 11/18/2016.
 */
public class ApplicationUserDetails implements UserDetails {

    private final User user;
    private final AuthorityRepository authorityRepository;

    public ApplicationUserDetails(User user, AuthorityRepository authorityRepository) {
        this.user = user;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorityRepository.findAllByRole(role).forEach(authority -> authorities.add(new SimpleGrantedAuthority(authority.getName())));
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !user.getAccountExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !user.getCredentialExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }

    public User getUser() {
        return user;
    }
}
