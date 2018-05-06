package com.github.olegbal.ticketservice.services.ticket;

import com.github.olegbal.ticketservice.data.TicketCreatorDto;
import com.github.olegbal.ticketservice.entities.Event;
import com.github.olegbal.ticketservice.entities.Ticket;
import com.github.olegbal.ticketservice.entities.TicketType;
import com.github.olegbal.ticketservice.services.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultTicketOperatorService implements TicketOperatorService {

    private final TicketService ticketService;
    private final TicketTypeService ticketTypeService;
    private final EventService eventService;

    @Override
    public List<Ticket> createTickets(List<TicketCreatorDto> dtos, long eventId) {

        Event event = eventService.getEventById(eventId);

        for (TicketCreatorDto dto : dtos) {

            TicketType ticketType = dto.getTicketType();
            ticketType.setId(-1);
            ticketType = ticketTypeService.createTicketType(ticketType);

            List<Ticket> creatingTickets = new ArrayList<>();
            for (int i = 0; i < dto.getAmountOfTickets(); i++) {
                creatingTickets.add(new Ticket(-1, dto.getPrice(), 0, event, ticketType, null));
            }

            ticketService.createTickets(creatingTickets);
        }

        return eventService.getEventById(eventId).getTickets();
    }

    @Override
    public void removeAllTicketsAndTypes() {
        ticketService.removeAllTickets();
        ticketTypeService.removeAllTypes();
    }
}
