package com.cars.trip.onlinetrips.dto;

import com.cars.trip.onlinetrips.authentication.model.User;

import java.util.Date;
import java.util.List;

public class AppEventDTO {
    private Long id;

    private List<User> singedUsers;

    private String start;

    private String destination;

    private String carCulture;

    private String distance;

    private String duration;

    private Date dateOfEvent;

    private String description;

    public AppEventDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getSingedUsers() {
        return singedUsers;
    }

    public void setSingedUsers(List<User> singedUsers) {
        this.singedUsers = singedUsers;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCarCulture() {
        return carCulture;
    }

    public void setCarCulture(String carCulture) {
        this.carCulture = carCulture;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getDateOfEvent() {
        return dateOfEvent;
    }

    public void setDateOfEvent(Date dateOfEvent) {
        this.dateOfEvent = dateOfEvent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
