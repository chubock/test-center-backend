package com.ztc.testcenter.repository.user;

import com.ztc.testcenter.domain.user.ActionCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Yubar on 4/8/2017.
 */
public interface ActionCodeRepository extends JpaRepository<ActionCode, Long> {

    ActionCode findByCode(String code);

}
