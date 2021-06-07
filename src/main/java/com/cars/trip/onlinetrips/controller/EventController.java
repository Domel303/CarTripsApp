package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.authentication.security.services.UserPrinciple;
import com.cars.trip.onlinetrips.dto.AppEventDTO;
import com.cars.trip.onlinetrips.entity.AppEvent;
import com.cars.trip.onlinetrips.dto.UserEventDTO;
import com.cars.trip.onlinetrips.service.EventService;
import com.cars.trip.onlinetrips.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createNewEvent(@RequestBody AppEventDTO eventDTO) {
        eventService.saveEvent(eventDTO);
    }



    @DeleteMapping(path = "/{eventId}")
    public void deleteCar(@PathVariable("eventId") Long id) {
        eventService.deleteEvent(id);
    }

    @PutMapping(path = "/update")
    public void updateEvent(@RequestBody AppEventDTO event) {
        eventService.updateEvent(event.getId(), event.getStart(), event.getDestination(), event.getCarCulture(), event.getDistance(), event.getDuration(), event.getDateOfEvent(), event.getDescription());
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('USER')")
    public void registerIntoEvent(@RequestBody UserEventDTO userEventDTO) {
        UserPrinciple principles = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByUsername(principles.getUsername());
        AppEvent event = eventService.getEvent(userEventDTO.getEventId());
        event.getSingedUsers().add(user);
        eventService.saveEvent(event);
    }

}
