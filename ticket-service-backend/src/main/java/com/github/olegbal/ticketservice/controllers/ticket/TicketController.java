package com.github.olegbal.ticketservice.controllers.ticket;

import com.github.olegbal.ticketservice.data.TicketCreatorDto;
import com.github.olegbal.ticketservice.entities.Ticket;
import com.github.olegbal.ticketservice.services.ticket.TicketOperatorService;
import com.github.olegbal.ticketservice.services.ticket.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@RestController
@RequestMapping(path = V1)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TicketController {

    private final TicketService ticketService;
    private final TicketOperatorService ticketOperatorService;

    @GetMapping(path = "/tickets", params = "eventId")
    public ResponseEntity getEventsTickets(@RequestParam long eventId) {

        List<Ticket> eventTickets = ticketService.getTicketsByEventId(eventId);

        return new ResponseEntity<>(eventTickets, HttpStatus.OK);
    }

    @GetMapping(path = "/tickets", params = "ticketId")
    public ResponseEntity getTicketsByIds(@RequestParam(value = "ticketId") List<Long> ticketIds) {
        List<Ticket> ticketList = ticketService.getTicketsByIds(ticketIds);
        return new ResponseEntity<>(ticketList, HttpStatus.OK);
    }

    @PostMapping(path = "/tickets", params = "eventId")
    public ResponseEntity createTickets(@RequestBody List<TicketCreatorDto> ticketCreatorDtos, @RequestParam long eventId) {
        List<Ticket> createdTickets = ticketOperatorService.createTickets(ticketCreatorDtos, eventId);
        return new ResponseEntity<>(createdTickets, HttpStatus.OK);
    }

    @PutMapping(path = "/tickets")
    public ResponseEntity updateAllSameTickets(@RequestBody Ticket ticket) {
        List<Ticket> updatedTickets = ticketService.updateTickets(ticket);
        return new ResponseEntity<>(updatedTickets, HttpStatus.OK);
    }

    @DeleteMapping(path = "/tickets/{id}")
    public ResponseEntity deleteTicket(@PathVariable long id) {
        if (ticketService.removeTicket(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
