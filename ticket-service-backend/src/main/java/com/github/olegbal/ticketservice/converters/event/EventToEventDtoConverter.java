package com.github.olegbal.ticketservice.converters.event;

import com.github.olegbal.ticketservice.data.EventDto;
import com.github.olegbal.ticketservice.entities.Event;
import com.github.olegbal.ticketservice.entities.Ticket;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;

@Component
public class EventToEventDtoConverter implements Converter<Event, EventDto> {

    @Override
    public EventDto convert(Event source) {

        EventDto eventDto = new EventDto();
        eventDto.setDate(source.getData());
        eventDto.setId(source.getId());
        eventDto.setImgUrl(source.getImgUrl());
        eventDto.setTitle(source.getTitle());
        eventDto.setEventPlace(source.getEventPlace());

        BigDecimal minPrice = source.getTickets().stream().min(Comparator.comparing(Ticket::getTicketPrice)).get().getTicketPrice();
        ;
        BigDecimal maxPrice = source.getTickets().stream().max(Comparator.comparing(Ticket::getTicketPrice)).get().getTicketPrice();

        eventDto.setMinPrice(minPrice);
        eventDto.setMaxPrice(maxPrice);

        return eventDto;
    }
}
