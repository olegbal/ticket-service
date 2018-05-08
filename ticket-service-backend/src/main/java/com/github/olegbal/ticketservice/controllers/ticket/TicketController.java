package com.github.olegbal.ticketservice.controllers.ticket;

import com.github.olegbal.ticketservice.entities.Ticket;
import com.github.olegbal.ticketservice.services.ticket.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@RestController
@RequestMapping(path = V1)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TicketController {

    private final TicketService ticketService;

    @GetMapping(path = "/tickets", params = "eventId")
    public ResponseEntity getEventsTickets(@RequestParam long eventId) {

        List<Ticket> eventTickets = ticketService.getTicketsByEventId(eventId);

        return new ResponseEntity<>(eventTickets, HttpStatus.OK);
    }
}
