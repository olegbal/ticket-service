package com.github.olegbal.ticketservice.converters.event;

import com.github.olegbal.ticketservice.data.EventDto;
import com.github.olegbal.ticketservice.entities.Event;
import com.github.olegbal.ticketservice.services.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventDtoToEventConverter implements Converter<EventDto, Event> {

    private final EventService eventService;

    @Override
    public Event convert(EventDto source) {

        Event event = eventService.getEventById(source.getId());
        event.setData(source.getDate());
        event.setEventPlace(source.getEventPlace());
        event.setImgUrl(source.getImgUrl());
        event.setTitle(source.getTitle());
        return event;
    }
}
