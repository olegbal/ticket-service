package com.github.olegbal.ticketservice.controllers.order;

import com.github.olegbal.ticketservice.data.OrderDto;
import com.github.olegbal.ticketservice.entities.Order;
import com.github.olegbal.ticketservice.services.orders.OrderOperator;
import com.github.olegbal.ticketservice.services.orders.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@RestController
@RequestMapping(path = V1)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService orderService;
    private final OrderOperator orderOperator;

    @GetMapping(path = "/orders", params = {"userId"})
    public ResponseEntity getUsersOrders(@RequestParam long userId) {
        return new ResponseEntity<>(orderService.getOrderById(userId), HttpStatus.OK);
    }

    @PostMapping(path = "/orders")
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto) {
        Order order = orderOperator.createOrder(orderDto.getOrderedTicketsIds(), orderDto.getUserId());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
