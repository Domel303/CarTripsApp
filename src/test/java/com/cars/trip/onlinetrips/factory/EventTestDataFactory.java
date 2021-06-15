package com.cars.trip.onlinetrips.factory;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.entity.AppEvent;
import com.cars.trip.onlinetrips.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class EventTestDataFactory {
    @Autowired
    private EventRepository eventRepository;

    public AppEvent addEvent(){
        AppEvent event = new AppEvent();
        Date date = new Date();
        event.setSingedUsers(List.of(new User("name",
                "lastname",
                "username",
                "email",
                "password")));
        event.setStart("Start");
        event.setDestination("Destination");
        event.setCarCulture("CarCulture");
        event.setDistance("Distance");
        event.setDuration("Duration");
        event.setDateOfEvent(date);
        event.setDescription("Description");

        eventRepository.save(event);
        return event;
    }
}
