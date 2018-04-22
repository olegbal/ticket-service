package com.github.olegbal.ticketservice.controllers.event;

import com.github.olegbal.ticketservice.data.EventDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.github.olegbal.ticketservice.enums.ApiVersioningUrlPrefix.V1;

@RestController
@RequestMapping(path = V1)
public class EventController {

    @GetMapping(path = "/events")
    public ResponseEntity getEvents() {
        List<EventDto> eventDtoList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            EventDto event = new EventDto("1", "Event title!", new Date(1995, 05, 25), new BigDecimal("100"),
                    new BigDecimal("100000"),
                    "https://www.kvitki.by/imageGenerator/355ce3ac3f02f72dac89b1aef3f11956/concertShort", "Soviet str 21");
            eventDtoList.add(event);
        }
        return new ResponseEntity<>(eventDtoList, HttpStatus.OK);
    }

    @GetMapping(path = "/events/{id}")
    public ResponseEntity getEvent(@PathVariable String id) {
        EventDto event = new EventDto("1", "Event title!", new Date(1995, 05, 25), new BigDecimal("100"),
                new BigDecimal("100000"),
                "https://www.kvitki.by/imageGenerator/355ce3ac3f02f72dac89b1aef3f11956/concertShort", "Soviet str 21");
        return new ResponseEntity<>(event, HttpStatus.OK);
    }
}
