package com.github.olegbal.ticketservice.repositories;

import com.github.olegbal.ticketservice.entities.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

    Order getOrderByUserId(long id);
}
