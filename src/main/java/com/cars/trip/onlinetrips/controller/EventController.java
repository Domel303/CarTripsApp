package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.authentication.security.services.UserPrinciple;
import com.cars.trip.onlinetrips.dto.AppEventDTO;
import com.cars.trip.onlinetrips.entity.AppEvent;
import com.cars.trip.onlinetrips.service.EventService;
import com.cars.trip.onlinetrips.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;
    private final UserService userService;

    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping
    public Page<AppEvent> getAllEvents(@RequestParam(required = false) int page,
                                       @RequestParam(required = false) int size) {
        return eventService.getAllEvents(page, size);
    }

    @GetMapping(path = "/updateEvent/{eventId}")
    public AppEvent getEvent(@PathVariable("eventId") Long id) {
        return eventService.getEvent(id);
    }


    @GetMapping(path = "/event")
    public List<AppEvent> getUsersEvents() {
        UserPrinciple principles = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByUsername(principles.getUsername());
        return eventService.getUsersEvents(user);
    }

    @GetMapping(path = "/user/{eventId}")
    public List<User> getEventsUsers(@PathVariable("eventId") Long id) {
        return eventService.getEventsUsers(id);
    }


    @PostMapping
    public AppEvent createNewEvent(@RequestBody AppEventDTO eventDTO) {
        return eventService.saveEvent(eventDTO);
    }


    @DeleteMapping(path = "/{eventId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteEvent(@PathVariable("eventId") Long id) {
        eventService.deleteEvent(id);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateEvent(@RequestBody AppEventDTO event) {
        eventService.updateEvent(event.getId(), event.getStart(), event.getDestination(), event.getCarCulture(), event.getDistance(), event.getDuration(), event.getDateOfEvent(), event.getDescription());
    }

    @PostMapping("/register")
    public void registerIntoEvent(@RequestParam Long id) {
        UserPrinciple principles = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByUsername(principles.getUsername());
        AppEvent event = eventService.getEvent(id);
        event.getSingedUsers().add(user);
        eventService.saveEvent(event);
    }

    public void registerIntoEventTest(Long id, String userName) {
        User user = userService.getUserByUsername(userName);
        AppEvent event = eventService.getEvent(id);
        event.getSingedUsers().add(user);
        eventService.saveEvent(event);
    }

    @PostMapping("/unregister")
    public void unregisterFromEvent(@RequestParam Long id) {
        UserPrinciple principles = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByUsername(principles.getUsername());
        AppEvent event = eventService.getEvent(id);
        event.getSingedUsers().remove(user);
        eventService.saveEvent(event);
    }
}
