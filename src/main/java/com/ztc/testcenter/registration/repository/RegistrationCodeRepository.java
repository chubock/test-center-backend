package com.ztc.testcenter.registration.repository;

import com.ztc.testcenter.registration.domain.RegistrationCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yubar on 9/8/17.
 */
public interface RegistrationCodeRepository extends JpaRepository<RegistrationCode, Long> {

    RegistrationCode findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumberAndUsedIsTrue(String phoneNumber);

}
