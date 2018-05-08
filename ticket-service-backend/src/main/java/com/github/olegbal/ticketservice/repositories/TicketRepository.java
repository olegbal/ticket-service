package com.github.olegbal.ticketservice.repositories;

import com.github.olegbal.ticketservice.entities.Ticket;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TicketRepository extends PagingAndSortingRepository<Ticket, Long> {

    List<Ticket> getByTicketTypeId(long id);

    List<Ticket> getTicketsByEventId(long id);
}
