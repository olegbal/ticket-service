package com.github.olegbal.ticketservice.services.ticket;

import com.github.olegbal.ticketservice.data.TicketCreatorDto;
import com.github.olegbal.ticketservice.entities.Ticket;

import java.util.List;

public interface TicketOperatorService {

    List<Ticket> createTickets(List<TicketCreatorDto> dtos, long eventId);
}
