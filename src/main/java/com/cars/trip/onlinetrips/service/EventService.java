package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.entity.AppEvents;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface EventService {


    List<AppEvents> getAllEvents();

    void saveEvent(AppEvents event);

    void deleteEvent(Long id);

    void updateEvent(Long eventId,
                     String start,
                     String destination,
                     String carCulture,
                     String distance,
                     String duration,
                     Date dateOfEvent,
                     String description);

    AppEvents getEvent(Long id);
}
