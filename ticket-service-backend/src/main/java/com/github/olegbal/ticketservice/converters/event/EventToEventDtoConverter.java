package com.github.olegbal.ticketservice.converters.event;

import com.github.olegbal.ticketservice.data.EventDto;
import com.github.olegbal.ticketservice.entities.Event;
import com.github.olegbal.ticketservice.entities.Ticket;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.function.Supplier;

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

//        Ticket minPriceTicket = source.getTickets().stream().min(Comparator.comparing(Ticket::getTicketPrice))
//                .orElseGet(null);
//
//        BigDecimal minPrice = minPriceTicket == null ? new BigDecimal(0) : minPriceTicket.getTicketPrice();
//
//        Ticket maxPriceTicket = source.getTickets().stream().max(Comparator.comparing(Ticket::getTicketPrice))
//                .orElseGet(null);
//
//        BigDecimal maxPrice = maxPriceTicket == null ? new BigDecimal(0) : maxPriceTicket.getTicketPrice();
//
//        eventDto.setMinPrice(minPrice);
//        eventDto.setMaxPrice(maxPrice);
        //FIXME  ADD REAL DATA

        eventDto.setMinPrice(new BigDecimal(0));
        eventDto.setMaxPrice(new BigDecimal(1000));
        return eventDto;
    }
}
