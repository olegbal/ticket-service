package com.github.olegbal.ticketservice.repositories;

import com.github.olegbal.ticketservice.entities.Event;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

}
