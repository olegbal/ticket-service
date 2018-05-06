package com.github.olegbal.ticketservice.services.ticket;

import com.github.olegbal.ticketservice.entities.TicketType;

import java.util.List;

public interface TicketTypeService {

    TicketType getTicketById(long id);

    TicketType createTicketType(TicketType ticketTypes);

    TicketType updateTicketType(TicketType ticketType);

    List createTicketTypes(List<TicketType> ticketTypes);

    List<TicketType> updateTicketTypes(List<TicketType> ticketTypes);

    boolean removeTicketType(long id);

    void removeAllTypes();

}
