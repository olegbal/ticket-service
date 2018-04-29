package com.github.olegbal.ticketservice.repositories;

import com.github.olegbal.ticketservice.entities.EventPlace;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EventPlaceRepository extends PagingAndSortingRepository<EventPlace, Long> {

    EventPlace findByPlaceName(String placeName);
}
