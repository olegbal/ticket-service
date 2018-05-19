package com.github.olegbal.ticketservice.repositories;

import com.github.olegbal.ticketservice.entities.Order;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {

    List<Order> getOrderByUserId(long id);
}
