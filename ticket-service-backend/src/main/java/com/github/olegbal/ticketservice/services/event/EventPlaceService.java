package com.github.olegbal.ticketservice.services.event;

import com.github.olegbal.ticketservice.entities.EventPlace;

import java.util.List;

public interface EventPlaceService {

    EventPlace getPlaceById(long id);

    EventPlace getPlaceByName(String name);

    List<EventPlace> getAllEventPlaces();

    EventPlace createEventPlace(EventPlace eventPlace);

    EventPlace updateEventPlace(EventPlace eventPlace);

    boolean deleteEventPlaceById(long id);

    void removeAll();
}
