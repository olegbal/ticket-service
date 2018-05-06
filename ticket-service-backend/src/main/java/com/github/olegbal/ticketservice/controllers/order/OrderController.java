package com.github.olegbal.ticketservice.controllers.order;

import com.github.olegbal.ticketservice.services.orders.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@RestController
@RequestMapping(path = V1)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService orderService;

    @GetMapping(path = "/orders", params = {"userId"})
    public ResponseEntity getUsersOrders(@RequestParam long userId) {
        return new ResponseEntity<>(orderService.getOrderById(userId), HttpStatus.OK);
    }
}
