package com.github.olegbal.ticketservice.services.ticket;

import com.github.olegbal.ticketservice.entities.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> createTickets(List<Ticket> creatorDto);

    List<Ticket> updateTickets(Ticket ticket);

    Iterable<Ticket> updateTickets(List<Ticket> tickets);

    List<Ticket> getTicketsByIds(List<Long> ids);

    List<Ticket> getTicketsByEventId(long id);

    void removeAllTickets();
}
