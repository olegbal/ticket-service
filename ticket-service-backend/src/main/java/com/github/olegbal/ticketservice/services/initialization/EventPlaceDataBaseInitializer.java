package com.github.olegbal.ticketservice.services.initialization;

import com.github.olegbal.ticketservice.entities.EventPlace;
import com.github.olegbal.ticketservice.services.event.EventPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static com.github.olegbal.ticketservice.services.initialization.InitializationHelper.EVENT_PLACES_AMOUNT;

@Order(2)
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventPlaceDataBaseInitializer implements DataBaseInitializer {

    private final EventPlaceService eventPlaceService;

    @Override
    public void initializeData() {

        for (int i = 0; i < EVENT_PLACES_AMOUNT; i++) {
            createEventPlace(i);
        }
    }


    private void createEventPlace(int i) {
        EventPlace eventPlace = new EventPlace(i, "placeName" + i, "address " + i);
        eventPlaceService.createEventPlace(eventPlace);
    }
}
