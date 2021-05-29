package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.entity.AppEvents;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface EventService {


    public List<AppEvents> getAllEvents();

    public  void addNewEvent(AppEvents event);

    public void deleteEvent(Long id);

    public void updateEvent(Long eventId,
                            String start,
                            String destination,
                            String carCulture,
                            String distance,
                            String duration,
                            Date dateOfEvent,
                            String description);
}
