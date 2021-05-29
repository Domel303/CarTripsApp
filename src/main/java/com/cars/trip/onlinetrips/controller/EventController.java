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

    @DeleteMapping(path = "{eventId}")
    public void deleteCar(@PathVariable("carId") Long id) {
        eventService.deleteEvent(id);
    }

    @PutMapping(path = "{eventId}")
    public void updateEvent(
            @PathVariable("eventId") Long eventId,
            @RequestParam String start,
            @RequestParam String destination,
            @RequestParam String carCulture,
            @RequestParam String distance,
            @RequestParam String duration,
            @RequestParam Date dateOfEvent,
            @RequestParam String description

    ) {
        eventService.updateEvent(eventId, start,destination,carCulture,distance,duration,dateOfEvent,description);
    }

}
