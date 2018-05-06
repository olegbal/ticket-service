package com.github.olegbal.ticketservice.services.ticket;

import com.github.olegbal.ticketservice.entities.Ticket;
import com.github.olegbal.ticketservice.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultTicketService implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public List<Ticket> createTickets(List<Ticket> creatingDtoList) {
        for (Ticket ticket : creatingDtoList) {
            ticket.setId(-1);
        }
        return (List<Ticket>) ticketRepository.save(creatingDtoList);
    }

    @Override
    public List<Ticket> updateTickets(Ticket ticket) {

        List<Ticket> tickets = ticketRepository.getByTicketTypeId(ticket.getTicketType().getId());

        for (Ticket ticket1 : tickets) {
            ticket1.setTicketPrice(ticket.getTicketPrice());
            ticket1.setTicketState(ticket.getTicketState());
        }

        return (List<Ticket>) ticketRepository.save(tickets);
    }

    @Override
    public List<Ticket> updateTickets(List<Ticket> tickets) {
        return (List<Ticket>) ticketRepository.save(tickets);
    }

    @Override
    public List<Ticket> getTicketsByIds(List<Long> ids) {

        return (List<Ticket>) ticketRepository.findAll(ids);
    }

    @Override
    public void removeAllTickets() {
        ticketRepository.deleteAll();
    }
}
