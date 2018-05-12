package com.github.olegbal.ticketservice.services.initialization;

import com.github.olegbal.ticketservice.services.event.EventPlaceService;
import com.github.olegbal.ticketservice.services.event.EventService;
import com.github.olegbal.ticketservice.services.orders.OrderService;
import com.github.olegbal.ticketservice.services.ticket.TicketOperatorService;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Order(1)
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataBaseCleaner implements DataBaseInitializer {

    private final EventService eventService;
    private final EventPlaceService eventPlaceService;
    private final TicketOperatorService ticketOperatorService;
    private final UserInfoService userInfoService;
    private final OrderService orderService;


    @PostConstruct
    @Override
    public void initializeData() {
        ticketOperatorService.removeAllTicketsAndTypes();
        orderService.removeAllOrders();
        userInfoService.removeAllUsers();
        eventService.removeAll();
        eventPlaceService.removeAll();
    }
}
