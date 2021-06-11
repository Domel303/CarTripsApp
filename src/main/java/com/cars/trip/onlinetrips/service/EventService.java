package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.dto.AppEventDTO;
import com.cars.trip.onlinetrips.entity.AppEvent;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface EventService {


    Page<AppEvent> getAllEvents(int page, int size);

    AppEvent saveEvent(AppEventDTO event);

    void saveEvent(AppEvent event);

    List<AppEvent> getUsersEvents(User user);

    List<User> getEventsUsers(Long id);

    void deleteEvent(Long id);

    void updateEvent(Long eventId,
                     String start,
                     String destination,
                     String carCulture,
                     String distance,
                     String duration,
                     Date dateOfEvent,
                     String description);

    AppEvent getEvent(Long id);
}
