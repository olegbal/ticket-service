package com.github.olegbal.ticketservice.services.initialization;

import com.github.olegbal.ticketservice.data.TicketCreatorDto;
import com.github.olegbal.ticketservice.entities.Event;
import com.github.olegbal.ticketservice.entities.TicketType;
import com.github.olegbal.ticketservice.services.event.EventService;
import com.github.olegbal.ticketservice.services.ticket.TicketOperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.github.olegbal.ticketservice.services.initialization.InitializationHelper.EVENTS_AMOUNT;

@Order(5)
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TicketDataBaseInitializer implements DataBaseInitializer {

    private final TicketOperatorService ticketOperatorService;
    private final EventService eventService;

    @Override
    public void initializeData() {
        List<Event> eventList = eventService.getAllEvents();
        for (int i = 0; i < EVENTS_AMOUNT; i++) {
            createTickets(i, eventList.get(i).getId());
        }
    }

    private void createTickets(int i, long eventId) {
        List<TicketCreatorDto> ticketCreatorDtos = new ArrayList<>();
        TicketCreatorDto ticketCreatorDto1 = new TicketCreatorDto(new TicketType(-1,
                "Ticket Type description" + i, "TicketDescription" + i, null), 10, BigDecimal.valueOf(100));
        ticketCreatorDtos.add(ticketCreatorDto1);
        TicketCreatorDto ticketCreatorDto2 = new TicketCreatorDto(new TicketType(-1,
                "Ticket Type description" + i, "TicketDescription" + i, null), 5, BigDecimal.valueOf(2000));
        ticketCreatorDtos.add(ticketCreatorDto2);
        TicketCreatorDto ticketCreatorDto3 = new TicketCreatorDto(new TicketType(-1,
                "Ticket Type description" + i, "TicketDescription" + i, null), 3, BigDecimal.valueOf(30000));
        ticketCreatorDtos.add(ticketCreatorDto3);

        ticketOperatorService.createTickets(ticketCreatorDtos, eventId);
    }


}
