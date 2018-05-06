package com.github.olegbal.ticketservice.services.orders;

import com.github.olegbal.ticketservice.services.ticket.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderOperator {
    private final OrderService orderService;
    private final TicketService ticketService;
}
