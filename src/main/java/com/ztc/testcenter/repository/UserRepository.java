package com.ztc.testcenter.repository;

import com.ztc.testcenter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yubar on 2/27/17.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
