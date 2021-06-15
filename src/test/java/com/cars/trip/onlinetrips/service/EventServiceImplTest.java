package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.dto.AppEventDTO;
import com.cars.trip.onlinetrips.entity.AppEvent;
import com.cars.trip.onlinetrips.factory.EventTestDataFactory;
import com.cars.trip.onlinetrips.repository.EventRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class EventServiceImplTest {

    //předělat na groovy testy
    @Autowired
    EventServiceImpl eventService;

    EventTestDataFactory dataFactory;

    @Test
    void mapEventDTOToEvent() {
    //unit test logika
        Date date = new Date();

        AppEvent expected = dataFactory.addEvent(date);

        AppEventDTO eventDto = new AppEventDTO();
        expected.setSingedUsers(List.of(new User("name",
                "lastname",
                "username",
                "email",
                "password")));
        expected.setStart("Start");
        expected.setDestination("Destination");
        expected.setCarCulture("CarCulture");
        expected.setDistance("Distance");
        expected.setDuration("Duration");
        expected.setDateOfEvent(date);
        expected.setDescription("Description");

        AppEvent actual = eventService.mapEventDTOToEvent(eventDto);

        Assertions.assertEquals(expected, actual);
    }

    @Autowired
    EventRepository eventRepository;

    @Test
    void saveChangedAtributesToEvent(){
        //unit test logika
        //update eventu

        AppEvent event = new AppEvent();
        eventRepository.save(event);

        //update event

        //

        //assertEquals

    }

}