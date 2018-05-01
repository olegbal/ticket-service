package com.github.olegbal.ticketservice.repositories;

import com.github.olegbal.ticketservice.entities.TicketType;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TicketTypeRepository extends PagingAndSortingRepository<TicketType, Long> {

}
