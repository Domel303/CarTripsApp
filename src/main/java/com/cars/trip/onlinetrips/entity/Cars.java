package com.cars.trip.onlinetrips.entity;

import javax.persistence.*;

@Entity
@Table(name = "CARS")
public class Cars {

    @Id
    @SequenceGenerator(name="CAR_ID_SEQ", sequenceName = "CAR_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CAR_ID_SEQ")
    private Long id;

    @Column(name = "CAR_BRAND")
    private String carBrand;

    @Column(name = "COUNTRY_OF_ORIGIN")
    private String countryOfOrigin;

    @Column(name = "ENGINE_POWER_KW")
    private int enginePowerKW;

    @OneToOne(mappedBy = "usersCar")
    private AppUsers user;


    public Cars() {
    }
}
