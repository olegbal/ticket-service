package com.github.olegbal.ticketservice.services.event;

import com.github.olegbal.ticketservice.entities.Event;
import com.github.olegbal.ticketservice.repositories.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultEventService implements EventService {

    private final EventRepository eventRepository;

    @Override
    public List<Event> getAllEvents() {

        return (List<Event>) eventRepository.findAll();
    }

    @Override
    public List<Event> getEventsByUserId(long userId) {
        return eventRepository.getEventsByUserId(userId);
    }

    @Override
    public Event getEventById(long id) {
        return eventRepository.findOne(id);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event createEvent(Event event) {
        event.setId(-1);
        return eventRepository.save(event);
    }

    @Override
    public boolean deleteEvent(long id) {
        eventRepository.delete(id);
        return eventRepository.findOne(id) == null;
    }

    @Override
    public void removeAll() {
        eventRepository.deleteAll();
    }
}
