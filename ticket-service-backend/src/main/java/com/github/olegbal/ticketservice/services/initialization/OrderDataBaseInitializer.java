package com.github.olegbal.ticketservice.services.initialization;

import com.github.olegbal.ticketservice.entities.Event;
import com.github.olegbal.ticketservice.entities.Role;
import com.github.olegbal.ticketservice.entities.Ticket;
import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.enums.Roles;
import com.github.olegbal.ticketservice.services.event.EventService;
import com.github.olegbal.ticketservice.services.orders.OrderOperator;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Order(5)
@DependsOn(value = "userDataBaseInitializer")
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderDataBaseInitializer implements DataBaseInitializer {

    private final OrderOperator orderOperator;
    private final EventService eventService;
    private final UserInfoService userInfoService;

    @PostConstruct
    @Override
    public void initializeData() {

        List<User> users = userInfoService.getUsersByRoles(
                Collections.singleton(new Role(Roles.USER.roleId(), Roles.USER.roleName())));
        List<Event> events = eventService.getAllEvents();

        for (Event event : events) {
            int i = 0;
            for (User user : users) {
                com.github.olegbal.ticketservice.entities.Order order = new com.github.olegbal.ticketservice.entities.Order();
                List<Long> ticketIds = new ArrayList<>();
                ticketIds.add(event.getTickets().get(i).getId());
                ticketIds.add(event.getTickets().get(i + 1).getId());
                orderOperator.createOrder(ticketIds, user.getId());
                i += 2;
            }
        }
    }
}
