package com.github.olegbal.ticketservice.services.ticket;

import com.github.olegbal.ticketservice.entities.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> getTicketsByIds(List<Long> ids);

    List<Ticket> getTicketsByEventId(long id);

    List<Ticket> createTickets(List<Ticket> creatorDto);

    List<Ticket> updateTickets(Ticket ticket);

    List<Ticket> updateTickets(List<Ticket> tickets);

    boolean removeTicket(long id);

    void removeAllTickets();
}
