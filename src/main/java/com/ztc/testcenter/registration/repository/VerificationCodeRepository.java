package com.ztc.testcenter.registration.repository;


import com.ztc.testcenter.registration.domain.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * Created by yubar on 9/8/17.
 */
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    VerificationCode findByPhoneNumberAndExpireDateAfter(String phoneNumber, Date expireDateAfter);
    VerificationCode findFirstByPhoneNumberAndCodeOrderByCreateDateDesc(String phoneNumber, String code);

}
