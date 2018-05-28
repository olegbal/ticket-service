package com.github.olegbal.ticketservice.services.initialization;

import com.github.olegbal.ticketservice.entities.Event;
import com.github.olegbal.ticketservice.entities.EventPlace;
import com.github.olegbal.ticketservice.entities.Role;
import com.github.olegbal.ticketservice.entities.User;
import com.github.olegbal.ticketservice.enums.Roles;
import com.github.olegbal.ticketservice.services.event.EventPlaceService;
import com.github.olegbal.ticketservice.services.event.EventService;
import com.github.olegbal.ticketservice.services.user.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static com.github.olegbal.ticketservice.services.initialization.InitializationHelper.EVENT_PLACES_AMOUNT;

@Order(4)
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EventDataBaseInitalizer implements DataBaseInitializer {

    private final EventService eventService;
    private final EventPlaceService eventPlaceService;
    private final UserInfoService userInfoService;

    @Override
    public void initializeData() {
        List<EventPlace> eventPlaceList = eventPlaceService.getAllEventPlaces();
        List<User> users = userInfoService.getUsersByRoles(
                Collections.singleton(new Role(Roles.ORGANIZER.roleId(), Roles.ORGANIZER.roleName())));
        int eventPlaceCounter = 0;
        int organizersCounter = 0;
        for (int i = 0; i < InitializationHelper.EVENTS_AMOUNT; i += 2) {
            EventPlace eventPlace = eventPlaceList.get(eventPlaceCounter);
            User user = users.get(organizersCounter);
            createEvent(i, eventPlace, user);
            createEvent(i + 1, eventPlace, user);
            if (eventPlaceCounter != EVENT_PLACES_AMOUNT - 1) {
                eventPlaceCounter++;
            }
            if (organizersCounter != users.size() - 1)
                organizersCounter++;
        }
    }

    private void createEvent(int i, EventPlace eventPlace, User user) {
        //TODO ADD USERS
        Event event = new Event(i, "Event" + i, new Date(2018, 5, 30),
                "https://www.kvitki.by/imageGenerator/355ce3ac3f02f72dac89b1aef3f11956/concertShort", true, eventPlace, null, user);
        eventService.createEvent(event);
    }
}