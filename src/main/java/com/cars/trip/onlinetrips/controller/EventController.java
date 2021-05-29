package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.entity.AppEvents;
import com.cars.trip.onlinetrips.service.EventService;
import com.cars.trip.onlinetrips.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public EventController(EventService eventService, UserService userService)
    {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/allEvents")
    public List<AppEvents> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping
    public void createNewEvent(@RequestBody AppEvents event) {
        eventService.saveEvent(event);
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

    @PostMapping("/register")
    public void registerIntoEvent(@RequestParam String userName, @RequestParam Long eventId){
        User user = userService.getUserByUsername(userName);
        AppEvents event = eventService.getEvent(eventId);
        event.getSingedUsers().add(user);
        eventService.saveEvent(event);
    }

}
