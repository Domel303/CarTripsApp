package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.authentication.repository.UserRepository;
import com.cars.trip.onlinetrips.entity.AppEvent;
import com.cars.trip.onlinetrips.factory.Creator;
import com.cars.trip.onlinetrips.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.List;

class EventControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    Creator creator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    EventController eventController;


    @Test
    void registerIntoEvent() {
        Date date = new Date();
        AppEvent event = new AppEvent();
        event.setSingedUsers(List.of());
        event.setStart("Start");
        event.setDestination("Destination");
        event.setCarCulture("CarCulture");
        event.setDistance("Distance");
        event.setDuration("Duration");
        event.setDateOfEvent(date);
        event.setDescription("Description");

        AppEvent databaseEvent = (AppEvent) creator.saveEntity(event);

        eventController.registerIntoEvent(databaseEvent.getId());
        AppEvent finalEvent = eventRepository.findById(databaseEvent.getId()).orElseThrow();

        Assertions.assertEquals(finalEvent.getSingedUsers().size(), 1);

    }
}