package com.github.olegbal.ticketservice.integration.services.cascades;

import com.github.olegbal.ticketservice.services.event.DefaultEventPlaceService;
import com.github.olegbal.ticketservice.services.initialization.EventDataBaseInitalizer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventPlaceServiceCascadeTest {

    @Autowired
    private EventDataBaseInitalizer eventDataBaseInitalizer;
    @Autowired
    private DefaultEventPlaceService eventPlaceService;


    @Before
    public void dbInstallations() {
    }

    @Test
    public void updateTest() {

    }

    @Test
    public void createTest() {

    }

    @Test
    public void deleteTest() {

    }

}
