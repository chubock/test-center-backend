package com.ztc.testcenter.user.repository;

import com.ztc.testcenter.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yubar on 2/27/17.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
