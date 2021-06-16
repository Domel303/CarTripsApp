package com.cars.trip.onlinetrips.entity;

import com.cars.trip.onlinetrips.authentication.model.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "APP_EVENTS")
public class AppEvent {

    @Id
    @SequenceGenerator(name = "APP_EVENT_ID_SEQ", sequenceName = "APP_EVENT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APP_EVENT_ID_SEQ")
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "SINGED_USERS_INTO_EVENT",
            joinColumns = @JoinColumn(name = "EVENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private List<User> singedUsers;

    @Column(name = "START")
    private String start;

    @Column(name = "DESTINATION")
    private String destination;

    @Column(name = "CAR_CULTURE")
    private String carCulture;

    @Column(name = "DISTANCE")
    private String distance;

    @Column(name = "DURATION")
    private String duration;

    @Column(name = "DATE_OF_EVENT")
    private Date dateOfEvent;

    @Column(name = "DESCRIPION")
    private String description;

    public AppEvent() {
    }

    public AppEvent(List<User> singedUsers,
                    String start,
                    String destination,
                    String carCulture,
                    String distance,
                    String duration,
                    Date dateOfEvent,
                    String description) {
        this.singedUsers = singedUsers;
        this.start = start;
        this.destination = destination;
        this.carCulture = carCulture;
        this.distance = distance;
        this.duration = duration;
        this.dateOfEvent = dateOfEvent;
        this.description = description;
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
