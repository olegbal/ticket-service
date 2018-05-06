package com.github.olegbal.ticketservice.services.orders;

import com.github.olegbal.ticketservice.entities.Order;
import com.github.olegbal.ticketservice.entities.Ticket;
import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.services.ticket.TicketService;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderOperator {
    private final OrderService orderService;
    private final TicketService ticketService;
    private final UserInfoService userInfoService;

    public Order createOrder(List<Long> ticketIds, Long userId) {
        Order order = new Order();
        List<Ticket> orderingTickets = ticketService.getTicketsByIds(ticketIds);
        User user = userInfoService.getUserById(userId);
        orderingTickets.forEach((ticket) -> ticket.setTicketState(1));
        order.setOrderDate(Date.valueOf(LocalDate.now()));
        order.setTicketList(orderingTickets);
        order.setUser(user);

        Order newOrder = orderService.createOrder(order);
        for (Ticket ticket : newOrder.getTicketList()) {
            ticket.setOrder(newOrder);
        }
        ticketService.updateTickets(newOrder.getTicketList());

        return newOrder;
    }

    public void removeAllOrders() {
        orderService.removeAllOrders();
    }
}
