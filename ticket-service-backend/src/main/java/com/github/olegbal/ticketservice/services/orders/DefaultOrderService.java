package com.github.olegbal.ticketservice.services.orders;

import com.github.olegbal.ticketservice.entities.Order;
import com.github.olegbal.ticketservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultOrderService implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order getOrderById(long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public Order getOrderByUserId(long userId) {
        return orderRepository.getOrderByUserId(userId);
    }

    @Override
    public Order createOrder(Order order) {
        order.setId(-1);
        order.getTicketList().forEach(ticket -> {
        });
        return orderRepository.save(order);
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
}
