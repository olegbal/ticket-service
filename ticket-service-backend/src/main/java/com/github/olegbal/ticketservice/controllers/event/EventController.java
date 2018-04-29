package com.github.olegbal.ticketservice.controllers.event;

import com.github.olegbal.ticketservice.entities.Event;
import com.github.olegbal.ticketservice.services.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@RestController
@RequestMapping(path = V1)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventController {

    private final EventService eventService;

    @GetMapping(path = "/events")
    public ResponseEntity getEvents() {
        return new ResponseEntity<>(eventService.getAllEvents(), HttpStatus.OK);
    }

    @GetMapping(path = "/events/{id}")
    public ResponseEntity getEvent(@PathVariable long id) {
        return new ResponseEntity<>(eventService.getEventById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/events")
    public ResponseEntity createEvent(@RequestBody Event event) {

        Event event1 = eventService.createEvent(event);

        return new ResponseEntity<>(event1, HttpStatus.OK);
    }

    @PutMapping(path = "/events")
    public ResponseEntity updateEvent(@RequestBody Event event) {

        Event event1 = eventService.updateEvent(event);

        return new ResponseEntity<>(event1, HttpStatus.OK);
    }

    @DeleteMapping(path = "/events/{id}")
    public ResponseEntity deleteEvent(@PathVariable long id) {
        if (eventService.deleteEvent(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
