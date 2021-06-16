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

    @Autowired
    EventServiceImpl eventService;

    @Autowired
    EventRepository eventRepository;

    @Test
    void mapEventDTOToEvent() {
        Date date = new Date();

        AppEvent expected = new AppEvent();
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

        AppEventDTO eventDto = new AppEventDTO();
        eventDto.setSingedUsers(List.of(new User("name",
                "lastname",
                "username",
                "email",
                "password")));
        eventDto.setStart("Start");
        eventDto.setDestination("Destination");
        eventDto.setCarCulture("CarCulture");
        eventDto.setDistance("Distance");
        eventDto.setDuration("Duration");
        eventDto.setDateOfEvent(date);
        eventDto.setDescription("Description");

        AppEvent actual = eventService.mapEventDTOToEvent(eventDto);


        Assertions.assertEquals(expected.getSingedUsers().get(0).getUsername(), actual.getSingedUsers().get(0).getUsername());
        Assertions.assertEquals(expected.getStart(), actual.getStart());
        Assertions.assertEquals(expected.getDestination(), actual.getDestination());
        Assertions.assertEquals(expected.getCarCulture(), actual.getCarCulture());
        Assertions.assertEquals(expected.getDistance(), actual.getDistance());
        Assertions.assertEquals(expected.getDuration(), actual.getDuration());
        Assertions.assertEquals(expected.getDateOfEvent(), actual.getDateOfEvent());
        Assertions.assertEquals(expected.getDescription(), actual.getDescription());
    }



    @Test
    void saveChangedAtributesToEvent() {
        Date date = new Date();

        AppEvent oldEvent = new AppEvent();
        oldEvent.setSingedUsers(List.of(new User("name",
                "lastname",
                "username",
                "email",
                "password")));
        oldEvent.setStart("Start");
        oldEvent.setDestination("Destination");
        oldEvent.setCarCulture("CarCulture");
        oldEvent.setDistance("Distance");
        oldEvent.setDuration("Duration");
        oldEvent.setDateOfEvent(date);
        oldEvent.setDescription("Description");

        eventRepository.save(oldEvent);

        User user = oldEvent.getSingedUsers().get(0);

        List<AppEvent> fromDatabase = eventRepository.findAllBySingedUsers(user);

        eventService.updateEvent(fromDatabase.get(0).getId(),
                "NewStart",
                "NewDestination",
                "NewCarCulture",
                "NewDistance",
                "NewDuration",
                null,
                "NewDescription");

        List<AppEvent> updatedFromDatabase = eventRepository.findAllBySingedUsers(user);
        AppEvent actual = updatedFromDatabase.get(0);

        Assertions.assertEquals("NewStart", actual.getStart());
        Assertions.assertEquals("NewDestination", actual.getDestination());
        Assertions.assertEquals("NewCarCulture", actual.getCarCulture());
        Assertions.assertEquals("NewDistance", actual.getDistance());
        Assertions.assertEquals("NewDuration", actual.getDuration());
        Assertions.assertEquals("NewDescription", actual.getDescription());

    }

}