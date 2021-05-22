package com.cars.trip.onlinetrips.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "APP_EVENTS")
public class AppEvents {

    @Id
    @SequenceGenerator(name = "APP_EVENT_ID_SEQ", sequenceName = "APP_EVENT_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APP_EVENT_ID_SEQ")
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "SINGED_USERS",
            joinColumns = @JoinColumn(name = "EVENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private Set<AppUsers> singedUsers;


}
