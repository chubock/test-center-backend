package com.ztc.testcenter.gre.service;

import com.ztc.testcenter.gre.domain.test.TestTicket;
import com.ztc.testcenter.gre.repository.test.TestTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yubar on 10/1/17.
 */

@Service
@Transactional
public class TestTicketService {

    private final TestTicketRepository testTicketRepository;

    @Autowired
    public TestTicketService(TestTicketRepository testTicketRepository) {
        this.testTicketRepository = testTicketRepository;
    }

    public void createTestTicketForUser(String username, Boolean free, Integer count) {

        List<TestTicket> tickets = new ArrayList<>();
        for (int i=0; i< count; i++)
            tickets.add(new TestTicket(username, free));

        testTicketRepository.save(tickets);

    }
}
