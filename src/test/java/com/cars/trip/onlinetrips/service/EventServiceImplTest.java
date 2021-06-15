package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.dto.AppEventDTO;
import com.cars.trip.onlinetrips.entity.AppEvent;
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
    @Test
    void mapEventDTOToEvent() {

        //creator addEvent() date probably problem.

      //  AppEvent actual = eventService.mapEventDTOToEvent(event);
//        AppEvent expected = new AppEvent(); //doplnit atributy
//
//        Assertions.assertEquals(expected, actual);
    }

    @Autowired
    EventRepository eventRepository;

    @Test
    void saveChangedAtributesToEvent(){
        AppEvent event = new AppEvent();
        eventRepository.save(event);

        //update event

        //

        //assertEquals

    }

}