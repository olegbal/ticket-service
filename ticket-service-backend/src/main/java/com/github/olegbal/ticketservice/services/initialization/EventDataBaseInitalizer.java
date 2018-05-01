package com.github.olegbal.ticketservice.services.initialization;

import com.github.olegbal.ticketservice.entities.Event;
import com.github.olegbal.ticketservice.entities.EventPlace;
import com.github.olegbal.ticketservice.services.event.EventPlaceService;
import com.github.olegbal.ticketservice.services.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventDataBaseInitalizer implements DataBaseInitializer {

    private final EventService eventService;
    private final EventPlaceService eventPlaceService;

    @PostConstruct
    @Override
    public void initializeData() {
        eventService.removeAll();
        eventPlaceService.removeAll();

        int j = 1;
        for (int i = 1; i < 21; i += 2) {
            EventPlace eventPlace = new EventPlace(i, "placeName" + i, "Soviet street " + i);
            eventPlace = eventPlaceService.createEventPlace(eventPlace);
            Event event1 = new Event(i, "Event" + i, new Date(2018, 5, 30),
                    "https://www.kvitki.by/imageGenerator/355ce3ac3f02f72dac89b1aef3f11956/concertShort", eventPlace, null);
            eventService.updateEvent(event1);
            Event event2 = new Event(i + 1, "Event" + i + 1, new Date(2018, 5, 30),
                    "https://www.kvitki.by/imageGenerator/355ce3ac3f02f72dac89b1aef3f11956/concertShort", eventPlace, null);
            eventService.updateEvent(event2);
        }
    }
}