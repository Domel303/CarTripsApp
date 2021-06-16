package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.OnlineTripsApplication;
import com.cars.trip.onlinetrips.authentication.model.Role;
import com.cars.trip.onlinetrips.authentication.model.RoleName;
import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.entity.AppEvent;
import com.cars.trip.onlinetrips.factory.Creator;
import com.cars.trip.onlinetrips.repository.AppEventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.util.Date;
import java.util.List;
import java.util.Set;

@SpringBootTest(classes = OnlineTripsApplication.class)
@ComponentScan(basePackages = {"com.cars.trip.onlinetrips"} )
@Import(Creator.class)
class EventControllerTest {

    @Autowired
    private AppEventRepository appEventRepository;

    @Autowired
    Creator creator;

    @Autowired
    EventController eventController;

    @Test
    void registerIntoEvent() {
        Date date = new Date();
        User user = new User("name1","lastname1","username1","email@email.com","password");
        Role role = new Role(RoleName.ROLE_ADMIN);
        role.setId(2L);
        user.setRoles(Set.of(role));

        AppEvent event = new AppEvent();
        event.setSingedUsers(List.of(user));
        event.setStart("Start");
        event.setDestination("Destination");
        event.setCarCulture("CarCulture");
        event.setDistance("Distance");
        event.setDuration("Duration");
        event.setDateOfEvent(date);
        event.setDescription("Description");

        AppEvent databaseEvent = (AppEvent) creator.saveEntity(event);

        eventController.registerIntoEventTest(databaseEvent.getId(),user);
        AppEvent finalEvent = appEventRepository.findById(databaseEvent.getId()).orElseThrow();

        Assertions.assertEquals(finalEvent.getSingedUsers().size(), 2);

    }
}