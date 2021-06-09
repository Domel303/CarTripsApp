package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.dto.AppEventDTO;
import com.cars.trip.onlinetrips.entity.AppEvent;
import com.cars.trip.onlinetrips.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Page<AppEvent> getAllEvents(int page, int size){
        if (!(page < 0 ||size <= 0)){
           PageRequest onPage= PageRequest.of(page, size);
           return eventRepository.findAll(onPage);
        }else{
            return Page.empty();
        }
    }

    @Override
    public List<AppEvent> getAllEventsNP(){
        return eventRepository.findAll();
    }

    @Override
    public AppEvent saveEvent(AppEventDTO eventDTO){
        AppEvent event = mapEventDTOToEvent(eventDTO);
       return eventRepository.save(event);
    }

    @Override
    public void saveEvent(AppEvent event){
        eventRepository.save(event);
    }

    private AppEvent mapEventDTOToEvent(AppEventDTO eventDTO) {
        AppEvent event = new AppEvent();
        event.setSingedUsers(eventDTO.getSingedUsers());
        event.setStart(eventDTO.getStart());
        event.setDestination(eventDTO.getDestination());
        event.setCarCulture(eventDTO.getCarCulture());
        event.setDistance(eventDTO.getDistance());
        event.setDuration(eventDTO.getDuration());
        event.setDateOfEvent(eventDTO.getDateOfEvent());
        event.setDescription(eventDTO.getDescription());

        return event;
    }
    @Override
    public void deleteEvent(Long id){
        boolean exist = eventRepository.existsById(id);
        if (!exist){
            throw new IllegalStateException("Event with id" + id + "does not exists");
        }
        eventRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateEvent(Long eventId,
                            String start,
                            String destination,
                            String carCulture,
                            String distance,
                            String duration,
                            Date dateOfEvent,
                            String description){

        AppEvent event = eventRepository.
                findById(eventId).
                orElseThrow(() -> new IllegalStateException("Event with id"+ eventId+ " could not be found") );

        if (start != null && start.length() > 0 && !Objects.equals(event.getStart(),start)){
            event.setStart(start);
        }

        if (destination != null && destination.length() > 0 && !Objects.equals(event.getDestination(),destination)){
            event.setDestination(destination);
        }

        if (carCulture != null && carCulture.length() > 0 && !Objects.equals(event.getCarCulture(),carCulture)){
            event.setCarCulture(carCulture);
        }

        if (distance != null && distance.length() > 0 && !Objects.equals(event.getDistance(),distance)){
            event.setDistance(distance);
        }

        if (duration != null && duration.length() > 0 && !Objects.equals(event.getDuration(),duration)){
            event.setDuration(duration);
        }

        if (dateOfEvent != null && !Objects.equals(event.getDateOfEvent(),dateOfEvent)){
            event.setDateOfEvent(dateOfEvent);
        }

        if (description != null && description.length() > 0 && !Objects.equals(event.getDescription(),description)){
            event.setDescription(description);
        }
    }

    @Override
    public AppEvent getEvent(Long id){
        return eventRepository.findById(id).orElseThrow(() -> new IllegalStateException("Event with id "+ id+ "could not be found"));
    }
}
