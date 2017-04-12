package com.ztc.testcenter.generator;

import com.ztc.testcenter.domain.user.Authority;
import com.ztc.testcenter.repository.user.AuthorityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yubar on 4/3/17.
 */

@Service
public class AuthoritiesGenerator {

    private final AuthorityRepository authorityRepository;

    public AuthoritiesGenerator(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Transactional
    private void createAuthority(int number) {
        Authority authority = new Authority("Authority " + number);
        authorityRepository.save(authority);
    }

    public void createAuthorities(int number) {
        for (int i=0; i<number; i++)
            createAuthority(i + 1);
    }

    public void createAuthorities() {
        authorityRepository.save(new Authority("questions.list"));
        authorityRepository.save(new Authority("questions.save"));
        authorityRepository.save(new Authority("questions.delete"));

        authorityRepository.save(new Authority("products.list"));
        authorityRepository.save(new Authority("products.get"));
        authorityRepository.save(new Authority("products.save"));
    }

}