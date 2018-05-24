package com.github.olegbal.ticketservice.repositories;

import com.github.olegbal.ticketservice.entities.Event;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    List<Event> getEventsByUserId(long id);

    List<Event> getEventsByApprovedIsTrue();

    List<Event> getEventsByApprovedIsFalse();
}
