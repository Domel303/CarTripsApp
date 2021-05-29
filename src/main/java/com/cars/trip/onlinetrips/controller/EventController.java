package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.entity.AppEvents;
import com.cars.trip.onlinetrips.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/allEvents")
    public List<AppEvents> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping
    public void createNewEvent(@RequestBody AppEvents event) {
        eventService.addNewEvent(event);
    }

    @PostMapping(path = "/delete")
    public void deleteCar(@RequestParam Long id) {
        eventService.deleteEvent(id);
    }

    @PutMapping(path = "/update")
    public void updateEvent(
            @RequestParam(required = true) Long eventId,
            @RequestParam(required = false) String start,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) String carCulture,
            @RequestParam(required = false) String distance,
            @RequestParam(required = false) String duration,
            @RequestParam(required = false) Date dateOfEvent,
            @RequestParam(required = false) String description

    ) {
        eventService.updateEvent(eventId, start,destination,carCulture,distance,duration,dateOfEvent,description);
    }

}
