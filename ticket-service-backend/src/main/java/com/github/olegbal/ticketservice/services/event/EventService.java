package com.github.olegbal.ticketservice.services.event;

import com.github.olegbal.ticketservice.entities.Event;

import java.util.List;

public interface EventService {

    List<Event> getAllEvents();

    List<Event> getApprovedEvents();

    List<Event> getUnapprovedEvents();

    List<Event> getEventsByUserId(long userId);

    Event getEventById(long id);

    Event updateEvent(Event event);

    Event createEvent(Event event);

    boolean deleteEvent(long id);

    void removeAll();

}
