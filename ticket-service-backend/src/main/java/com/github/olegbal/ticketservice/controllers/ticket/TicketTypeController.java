package com.github.olegbal.ticketservice.controllers.ticket;

import com.github.olegbal.ticketservice.entities.TicketType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@RestController
@RequestMapping(path = V1)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TicketTypeController {

    @GetMapping(path = "/ticket-types/{id}")
    public ResponseEntity getUserById(@PathVariable long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @GetMapping(path = "/ticket-types")
    public ResponseEntity getUserByLogin() {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PostMapping(path = "/ticket-types")
    public ResponseEntity createUser(@RequestBody TicketType ticketType) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @PutMapping(path = "/ticket-types")
    public ResponseEntity updateUser(@RequestBody TicketType ticketType) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @DeleteMapping(path = "/ticket-types/{id}")
    public ResponseEntity deleteUserById(@PathVariable long id) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
