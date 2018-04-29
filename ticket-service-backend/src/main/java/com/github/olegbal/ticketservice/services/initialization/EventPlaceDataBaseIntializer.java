package com.github.olegbal.ticketservice.services.initialization;

import com.github.olegbal.ticketservice.entities.EventPlace;
import com.github.olegbal.ticketservice.services.event.EventPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventPlaceDataBaseIntializer implements DataBaseInitializer {

    private final EventPlaceService eventPlaceService;

    @PostConstruct
    @Override
    public void initializeData() {
        for (int i = 1; i < 11; i++) {
            EventPlace eventPlace = new EventPlace(i, "placeName" + i, "Soviet street " + i);
            eventPlaceService.updateEventPlace(eventPlace);
        }

    }
}
