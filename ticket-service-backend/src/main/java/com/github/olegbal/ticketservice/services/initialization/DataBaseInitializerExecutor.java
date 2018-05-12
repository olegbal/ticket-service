package com.github.olegbal.ticketservice.services.initialization;

import com.github.olegbal.ticketservice.services.event.EventPlaceService;
import com.github.olegbal.ticketservice.services.event.EventService;
import com.github.olegbal.ticketservice.services.orders.OrderService;
import com.github.olegbal.ticketservice.services.ticket.TicketOperatorService;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataBaseInitializerExecutor {

    private final EventService eventService;
    private final EventPlaceService eventPlaceService;
    private final TicketOperatorService ticketOperatorService;
    private final UserInfoService userInfoService;
    private final OrderService orderService;
    private final List<DataBaseInitializer> dataBaseInitializers;

    @PostConstruct
    public void initializeData() {
        ticketOperatorService.removeAllTicketsAndTypes();
        orderService.removeAllOrders();
        eventService.removeAll();
        userInfoService.removeAllUsers();
        eventPlaceService.removeAll();
        for (DataBaseInitializer dataBaseInitializer : dataBaseInitializers) {
            dataBaseInitializer.initializeData();
        }
    }
}
