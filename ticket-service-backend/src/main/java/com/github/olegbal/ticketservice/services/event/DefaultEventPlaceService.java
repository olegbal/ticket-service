package com.github.olegbal.ticketservice.services.event;

import com.github.olegbal.ticketservice.entities.EventPlace;
import com.github.olegbal.ticketservice.repositories.EventPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultEventPlaceService implements EventPlaceService {

    private final EventPlaceRepository eventPlaceRepository;

    @Override
    public EventPlace getPlaceById(long id) {
        EventPlace eventPlace = eventPlaceRepository.findOne(id);
        if (eventPlace == null) return null;

        return eventPlace;
    }

    @Override
    public EventPlace getPlaceByName(String name) {
        EventPlace eventPlace = eventPlaceRepository.findByPlaceName(name);
        if (eventPlace == null) return null;

        return eventPlace;
    }

    @Override
    public EventPlace createEventPlace(EventPlace eventPlace) {
        eventPlace.setId(-1);

        return eventPlaceRepository.save(eventPlace);
    }

    @Override
    public EventPlace updateEventPlace(EventPlace eventPlace) {
        return eventPlaceRepository.save(eventPlace);
    }

    @Override
    public boolean deleteEventPlaceById(long id) {

        eventPlaceRepository.delete(id);

        return eventPlaceRepository.findOne(id) == null;

    }

    @Override
    public void removeAll() {
        eventPlaceRepository.deleteAll();
    }
}
