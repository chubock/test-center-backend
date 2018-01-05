package com.ztc.testcenter.gre.controller.ticket;

import com.ztc.testcenter.gre.dto.TestTicketDTO;
import com.ztc.testcenter.gre.service.TestTicketService;
import com.ztc.testcenter.util.SecurityUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yubar on 1/5/18.
 */

@RestController
public class GetTestTicketsController {

    private final TestTicketService testTicketService;

    public GetTestTicketsController(TestTicketService testTicketService) {
        this.testTicketService = testTicketService;
    }

    @GetMapping("/gre-service/test-tickets")
    @PreAuthorize("hasAuthority('STUDENT')")
    public List<TestTicketDTO> handle(Authentication authentication) {
        return testTicketService.getUserTestTickets(SecurityUtil.getUsername(authentication))
                .stream()
                .map(TestTicketDTO::valueOf)
                .collect(Collectors.toList());
    }
}
