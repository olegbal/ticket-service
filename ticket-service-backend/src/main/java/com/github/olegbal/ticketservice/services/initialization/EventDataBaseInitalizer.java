package com.github.olegbal.ticketservice.services.initialization;

import com.github.olegbal.ticketservice.data.TicketCreatorDto;
import com.github.olegbal.ticketservice.entities.Event;
import com.github.olegbal.ticketservice.entities.EventPlace;
import com.github.olegbal.ticketservice.entities.Ticket;
import com.github.olegbal.ticketservice.entities.TicketType;
import com.github.olegbal.ticketservice.services.event.EventPlaceService;
import com.github.olegbal.ticketservice.services.event.EventService;
import com.github.olegbal.ticketservice.services.ticket.TicketOperatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Order(3)
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventDataBaseInitalizer implements DataBaseInitializer {

    private final EventService eventService;
    private final EventPlaceService eventPlaceService;
    private final TicketOperatorService ticketOperatorService;

    @PostConstruct
    @Override
    public void initializeData() {
        for (int i = 1; i < InitializationHelper.EVENTS_AMOUNT + 2; i += 2) {
            EventPlace eventPlace = createEventPlace(i);
            Event event1 = createEvent(i, eventPlace);
            createTickets(i, event1.getId());
            Event event2 = createEvent(i, eventPlace);
            createTickets(i, event2.getId());
        }
    }

    private EventPlace createEventPlace(int i) {
        EventPlace eventPlace = new EventPlace(i, "placeName" + i, "address " + i);
        return eventPlaceService.createEventPlace(eventPlace);
    }

    private Event createEvent(int i, EventPlace eventPlace) {
        Event event = new Event(i, "Event" + i, new Date(2018, 5, 30),
                "https://www.kvitki.by/imageGenerator/355ce3ac3f02f72dac89b1aef3f11956/concertShort", eventPlace, null);
        return eventService.createEvent(event);
    }

    private List<Ticket> createTickets(int i, long eventId) {
        List<TicketCreatorDto> ticketCreatorDtos = new ArrayList<>();
        TicketCreatorDto ticketCreatorDto1 = new TicketCreatorDto(new TicketType(-1,
                "Ticket Type description" + i, "TicketDescription" + i), 10, BigDecimal.valueOf(100));
        ticketCreatorDtos.add(ticketCreatorDto1);
        TicketCreatorDto ticketCreatorDto2 = new TicketCreatorDto(new TicketType(-1,
                "Ticket Type description" + i, "TicketDescription" + i), 5, BigDecimal.valueOf(2000));
        ticketCreatorDtos.add(ticketCreatorDto2);
        TicketCreatorDto ticketCreatorDto3 = new TicketCreatorDto(new TicketType(-1,
                "Ticket Type description" + i, "TicketDescription" + i), 3, BigDecimal.valueOf(30000));
        ticketCreatorDtos.add(ticketCreatorDto3);

        return ticketOperatorService.createTickets(ticketCreatorDtos, eventId);
    }

}