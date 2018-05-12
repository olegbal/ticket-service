package com.github.olegbal.ticketservice.services.initialization;

import com.github.olegbal.ticketservice.entities.Event;
import com.github.olegbal.ticketservice.entities.Role;
import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.enums.Roles;
import com.github.olegbal.ticketservice.services.event.EventService;
import com.github.olegbal.ticketservice.services.orders.OrderService;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Order(6)
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderDataBaseInitializer implements DataBaseInitializer {

    private final OrderService orderService;
    private final EventService eventService;
    private final UserInfoService userInfoService;

    @Override
    public void initializeData() {

        List<User> users = userInfoService.getUsersByRoles(
                Collections.singleton(new Role(Roles.USER.roleId(), Roles.USER.roleName())));
        List<Event> events = eventService.getAllEvents();

        int i = 0;
        for (User user : users) {
            List<Long> ticketIds = new ArrayList<>();
            ticketIds.add(events.get(i).getTickets().get(0).getId());
            ticketIds.add(events.get(i + 1).getTickets().get(1).getId());
            orderService.createOrderOfTickets(ticketIds, user.getId());
            i += 2;
        }
    }
}
