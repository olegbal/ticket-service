package com.github.olegbal.ticketservice.controllers.event;

import com.github.olegbal.ticketservice.data.EventDto;
import com.github.olegbal.ticketservice.entities.Event;
import com.github.olegbal.ticketservice.services.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@RestController
@RequestMapping(path = V1)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventController {

    private final EventService eventService;
    private final ConversionService converter;

    @GetMapping(path = "/events")
    public ResponseEntity getEvents() {

        List<Event> eventList = eventService.getAllEvents();
        List<EventDto> convertedEvents = (List<EventDto>) converter.convert(eventList,
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Event.class)),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(EventDto.class)));
        return new ResponseEntity<>(convertedEvents, HttpStatus.OK);
    }

    @GetMapping(path = "/events/{id}")
    public ResponseEntity getEvent(@PathVariable long id) {

        Event event = eventService.getEventById(id);
        EventDto eventDto = converter.convert(event, EventDto.class);

        return new ResponseEntity<>(eventDto, HttpStatus.OK);
    }

    @PostMapping(path = "/events")
    public ResponseEntity createEvent(@RequestBody EventDto eventDto) {
        Event event = converter.convert(eventDto, Event.class);
        event = eventService.createEvent(event);
        EventDto eventDto1 = converter.convert(event, EventDto.class);
        return new ResponseEntity<>(eventDto1, HttpStatus.OK);
    }

    @PutMapping(path = "/events")
    public ResponseEntity updateEvent(@RequestBody EventDto eventDto) {
        Event event = converter.convert(eventDto, Event.class);
        event = eventService.createEvent(event);
        EventDto eventDto1 = converter.convert(event, EventDto.class);
        return new ResponseEntity<>(eventDto1, HttpStatus.OK);
    }

    @DeleteMapping(path = "/events/{id}")
    public ResponseEntity deleteEvent(@PathVariable long id) {
        if (eventService.deleteEvent(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
