package com.ztc.testcenter.gre.repository.test;

import com.ztc.testcenter.gre.domain.test.TestTicket;
import com.ztc.testcenter.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by yubar on 10/1/17.
 */
public interface TestTicketRepository extends JpaRepository<TestTicket, Long> {

    TestTicket findFirstByUsernameAndFreeAndTestIsNull(String username, Boolean free);

}
