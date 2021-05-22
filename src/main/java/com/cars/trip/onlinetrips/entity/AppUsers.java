package com.cars.trip.onlinetrips.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="APP_USERS")
public class AppUsers {

    @Id
    @SequenceGenerator(name="APP_USER_ID_SEQ", sequenceName = "APP_USER_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APP_USER_ID_SEQ")
    private Long id;

    @Column(name ="NAME")
    private String name;

    @Column(name ="SURNAME")
    private String surname;

    @OneToOne
    @JoinColumn(name="CAR_ID", referencedColumnName = "id")
    private Cars usersCar;

    @ManyToMany(mappedBy = "singedUsers")
    private Set<AppEvents> usersEvents;
}
