package com.github.olegbal.ticketservice.services.ticket;

import com.github.olegbal.ticketservice.entities.TicketType;
import com.github.olegbal.ticketservice.repositories.TicketTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultTicketTypeService implements TicketTypeService {

    private final TicketTypeRepository ticketTypeRepository;

    @Override
    public TicketType getTicketById(long id) {
        return ticketTypeRepository.findOne(id);
    }

    @Override
    public TicketType createTicketType(TicketType ticketType) {
        ticketType.setId(-1);
        return ticketTypeRepository.save(ticketType);
    }

    @Override
    public TicketType updateTicketType(TicketType ticketType) {
        return ticketTypeRepository.save(ticketType);
    }

    @Override
    public List<TicketType> createTicketTypes(List<TicketType> ticketTypes) {
        return (List<TicketType>) ticketTypeRepository.save(ticketTypes);
    }

    @Override
    public List<TicketType> updateTicketTypes(List<TicketType> ticketTypes) {
        return (List<TicketType>) ticketTypeRepository.save(ticketTypes);
    }

    @Override
    public boolean removeTicketType(long id) {

        ticketTypeRepository.delete(id);

        return ticketTypeRepository.findOne(id) == null;
    }

    @Override
    public void removeAllTypes() {
        ticketTypeRepository.deleteAll();
    }
}
