package com.github.olegbal.ticketservice.controllers.event;

import com.github.olegbal.ticketservice.data.EventDto;
import com.github.olegbal.ticketservice.entities.Event;
import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.services.event.EventService;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@RestController
@RequestMapping(path = V1)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventController {

    private final EventService eventService;
    private final ConversionService converter;
    private final UserInfoService userInfoService;

    @GetMapping(path = "/events")
    public ResponseEntity getEvents() {

        List<Event> eventList = eventService.getAllEvents();
        List<EventDto> convertedEvents = (List<EventDto>) converter.convert(eventList,
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Event.class)),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(EventDto.class)));
        return new ResponseEntity<>(convertedEvents, HttpStatus.OK);
    }

    @GetMapping(path = "/events", params = "approved")
    public ResponseEntity getApprovedEvents(@PathParam(value = "approved") boolean approved) {

        List<EventDto> convertedEvents;

        if (approved) {

            List<Event> approvedEvents = eventService.getApprovedEvents();
            convertedEvents = (List<EventDto>) converter.convert(approvedEvents,
                    TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Event.class)),
                    TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(EventDto.class)));
            return new ResponseEntity(convertedEvents, HttpStatus.OK);
        } else {
            List<Event> unapprovedEvents = eventService.getUnapprovedEvents();
            convertedEvents = (List<EventDto>) converter.convert(unapprovedEvents,
                    TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Event.class)),
                    TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(EventDto.class)));
            return new ResponseEntity(convertedEvents, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/events", params = {"approved", "userId"})
    public ResponseEntity getEventsById(@PathParam(value = "userId") long userId,
                                        @PathParam(value = "approved") boolean approved) {

        List<Event> userEvents = eventService.getEventsByUserId(userId);
        if (approved) {
            userEvents = userEvents.stream().filter(x -> x.isApproved()).collect(Collectors.toList());
        } else {
            userEvents = userEvents.stream().filter(x -> !x.isApproved()).collect(Collectors.toList());
        }

        List<EventDto> convertedEvents = (List<EventDto>) converter.convert(userEvents,
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Event.class)),
                TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(EventDto.class)));
        return new ResponseEntity(convertedEvents, HttpStatus.OK);
    }

    @GetMapping(path = "/events/{id}")
    public ResponseEntity getEvent(@PathVariable long id) {

        Event event = eventService.getEventById(id);
        EventDto eventDto = converter.convert(event, EventDto.class);

        return new ResponseEntity<>(eventDto, HttpStatus.OK);
    }

    @PostMapping(path = "/events", params = "userId")
    public ResponseEntity createEvent(@RequestBody EventDto eventDto, @PathParam(value = "userId") long userId) {
        Event event = converter.convert(eventDto, Event.class);
        event.setUser(userInfoService.getUserById(userId));
        event = eventService.createEvent(event);
        EventDto eventDto1 = converter.convert(event, EventDto.class);
        return new ResponseEntity<>(eventDto1, HttpStatus.OK);
    }

    @PutMapping(path = "/events")
    public ResponseEntity updateEvent(@RequestBody EventDto eventDto) {
        Event event = converter.convert(eventDto, Event.class);
        event = eventService.updateEvent(event);
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
