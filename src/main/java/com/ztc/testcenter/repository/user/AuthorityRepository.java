package com.ztc.testcenter.repository.user;

import com.ztc.testcenter.domain.user.Authority;
import com.ztc.testcenter.domain.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by yubar on 3/1/17.
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    @Query("select r.authorities from Role r where r = :role")
    List<Authority> findAllByRole(Role role);

}
