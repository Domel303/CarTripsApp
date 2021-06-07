package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.entity.AppEvent;
import com.cars.trip.onlinetrips.request.UserEvent;
import com.cars.trip.onlinetrips.service.EventService;
import com.cars.trip.onlinetrips.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/allEvents")
    public Page<AppEvent> getAllEvents(@RequestParam(required = false) int page,
                                       @RequestParam(required = false) int size) {
        return eventService.getAllEvents(page, size);
    }

    @GetMapping("/allEventsNP")
    public List<AppEvent> getAllEventsNP(){
        return eventService.getAllEventsNP();
    }

    @PostMapping
    public void createNewEvent(@RequestBody AppEvent event) {
        eventService.saveEvent(event);
    }

    @DeleteMapping(path = "/{eventId}")
    public void deleteCar(@PathVariable("eventId") Long id) {
        eventService.deleteEvent(id);
    }

    @PutMapping(path = "/update")
    public void updateEvent(@RequestBody AppEvent event) {
        eventService.updateEvent(event.getId(), event.getStart(), event.getDestination(), event.getCarCulture(), event.getDistance(), event.getDuration(), event.getDateOfEvent(), event.getDescription());
    }

    @PostMapping("/register")
    public void registerIntoEvent(@RequestBody UserEvent userEvent) {
        User user = userService.getUserByUsername(userEvent.getUserName());
        AppEvent event = eventService.getEvent(userEvent.getEventId());
        event.getSingedUsers().add(user);
        eventService.saveEvent(event);
    }

}
