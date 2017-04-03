package com.ztc.testcenter.repository;

import com.ztc.testcenter.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yubar on 4/3/17.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
