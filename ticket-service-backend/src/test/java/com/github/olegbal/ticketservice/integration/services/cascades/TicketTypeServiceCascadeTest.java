package com.github.olegbal.ticketservice.integration.services.cascades;

import com.github.olegbal.ticketservice.entities.TicketType;
import com.github.olegbal.ticketservice.integration.services.cascades.config.AllCascadeTestsConfig;
import com.github.olegbal.ticketservice.repositories.TicketTypeRepository;
import com.github.olegbal.ticketservice.services.initialization.DataBaseInitializerExecutor;
import com.github.olegbal.ticketservice.services.initialization.EventDataBaseInitalizer;
import com.github.olegbal.ticketservice.services.ticket.DefaultTicketService;
import com.github.olegbal.ticketservice.services.ticket.DefaultTicketTypeService;
import lombok.RequiredArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Import(AllCascadeTestsConfig.class)
public class TicketTypeServiceCascadeTest {

    private final DefaultTicketTypeService ticketTypeService;
    private final TicketTypeRepository ticketTypeRepository;
    private final DataBaseInitializerExecutor dataBaseInitializerExecutor;
    private final DefaultTicketService ticketService;
    private final EventDataBaseInitalizer eventDataBaseInitalizer;

    @Before
    public void cleanDb() {
        dataBaseInitializerExecutor.initializeData();
    }

    @Test
    public void testRemovingOrphans() {
        eventDataBaseInitalizer.initializeData();

        TicketType ticketType = ticketTypeRepository.findAll().iterator().next();

        long ticketTypeId = ticketType.getId();

        ticketTypeService.removeTicketType(ticketTypeId);

    }

}
