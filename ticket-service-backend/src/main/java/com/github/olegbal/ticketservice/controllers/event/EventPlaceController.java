package com.github.olegbal.ticketservice.controllers.event;

import com.github.olegbal.ticketservice.entities.EventPlace;
import com.github.olegbal.ticketservice.services.event.EventPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@RestController
@RequestMapping(path = V1)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventPlaceController {

    private final EventPlaceService eventPlaceService;

    @GetMapping(path = "/event-places", params = "placeId")
    public ResponseEntity getEventPlaceById(@RequestParam long placeId) {
        EventPlace eventPlace = eventPlaceService.getPlaceById(placeId);

        if (eventPlace != null) {
            return new ResponseEntity<>(eventPlace, HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/event-places", params = "name")
    public ResponseEntity getEventPlaceByName(@RequestParam String name) {
        EventPlace eventPlace = eventPlaceService.getPlaceByName(name);

        if (eventPlace != null) {
            return new ResponseEntity<>(eventPlace, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/event-places")
    public ResponseEntity createEventPlace(@RequestBody EventPlace place) {
        EventPlace updatedEventPlace = eventPlaceService.createEventPlace(place);
        return new ResponseEntity<>(updatedEventPlace, HttpStatus.CREATED);
    }

    @PutMapping(path = "/event-places")
    public ResponseEntity updateEventPlace(@RequestBody EventPlace place) {
        EventPlace updatedEventPlace = eventPlaceService.updateEventPlace(place);
        return new ResponseEntity<>(updatedEventPlace, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/event-places/{id}")
    public ResponseEntity deleteEventPlace(@PathVariable long id) {
        if (eventPlaceService.deleteEventPlaceById(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
