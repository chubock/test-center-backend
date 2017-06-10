package com.ztc.testcenter.repository.user;

import com.ztc.testcenter.domain.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by yubar on 4/3/17.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Query("select r from Role r join fetch r.authorities where r.id = :id")
    Role findOneWithAuthorities(@Param("id") Long id);
}
