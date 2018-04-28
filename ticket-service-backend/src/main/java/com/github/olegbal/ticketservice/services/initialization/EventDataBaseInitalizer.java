package com.github.olegbal.ticketservice.services.initialization;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventDataBaseInitalizer implements DataBaseInitializer {



    @PostConstruct
    @Override
    public void initializeData() {

    }
}
