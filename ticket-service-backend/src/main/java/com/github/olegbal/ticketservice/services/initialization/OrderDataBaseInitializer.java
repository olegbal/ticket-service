package com.github.olegbal.ticketservice.services.initialization;

import com.github.olegbal.ticketservice.services.event.EventService;
import com.github.olegbal.ticketservice.services.orders.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Order(3)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderDataBaseInitializer implements DataBaseInitializer {

    private final EventService eventService;
    private final OrderService orderService;

    @PostConstruct
    @Override
    public void initializeData() {

    }
}
