package com.github.olegbal.ticketservice.controllers.order;

import com.github.olegbal.ticketservice.data.OrderDto;
import com.github.olegbal.ticketservice.entities.Order;
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

    @GetMapping(path = "/orders", params = {"userId"})
    public ResponseEntity getUsersOrders(@RequestParam long userId) {
        return new ResponseEntity<>(orderService.getOrderByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(path = "/orders/{orderId}")
    public ResponseEntity getOrderById(@PathVariable long orderId) {
        Order order = orderService.getOrderById(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping(path = "/orders")
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto) {
        Order order = orderService.createOrderOfTickets(orderDto.getOrderedTicketsIds(), orderDto.getUserId());
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping(path = "/orders")
    public ResponseEntity updateOrder() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping(path = "/orders/{id}")
    public ResponseEntity deleteOrderById(@PathVariable long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
