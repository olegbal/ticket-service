package com.github.olegbal.ticketservice.services.orders;

import com.github.olegbal.ticketservice.entities.Order;
import com.github.olegbal.ticketservice.entities.Ticket;
import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.repositories.OrderRepository;
import com.github.olegbal.ticketservice.services.ticket.TicketService;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;
    private final TicketService ticketService;
    private final UserInfoService userInfoService;

    @Override
    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    @Override
    public Order getOrderById(long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public List<Order> getOrderByUserId(long userId) {
        return orderRepository.getOrderByUserId(userId);
    }

    @Override
    public Order createOrder(Order order) {
        order.setId(-1);
        Order order1 = orderRepository.save(order);
        order1.getTicketList().forEach(ticket -> ticket.setOrder(order1));
        ticketService.updateTickets(order1.getTicketList());

        return order1;
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public boolean deleteOrder(long id) {
        orderRepository.delete(id);
        return orderRepository.findOne(id) == null;
    }

    @Override
    public void removeAllOrders() {
        orderRepository.deleteAll();
    }

    public Order createOrderOfTickets(List<Long> ticketIds, Long userId) {
        Order order = new Order();
        List<Ticket> orderingTickets = ticketService.getTicketsByIds(ticketIds);
        User user = userInfoService.getUserById(userId);
        orderingTickets.forEach((ticket) -> ticket.setTicketState(1));
        order.setOrderDate(DateTime.now());
        order.setTicketList(orderingTickets);
        order.setUser(user);


        return createOrder(order);
    }
}
