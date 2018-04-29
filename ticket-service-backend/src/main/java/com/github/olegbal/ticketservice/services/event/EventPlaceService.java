package com.github.olegbal.ticketservice.services.event;

import com.github.olegbal.ticketservice.entities.EventPlace;

public interface EventPlaceService {

    EventPlace getPlaceById(long id);

    EventPlace getPlaceByName(String name);

    EventPlace createEventPlace(EventPlace eventPlace);

    EventPlace updateEventPlace(EventPlace eventPlace);

    boolean deleteEventPlaceById(long id);

    void removeAll();
}
