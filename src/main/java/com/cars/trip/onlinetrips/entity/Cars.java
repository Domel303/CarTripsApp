package com.cars.trip.onlinetrips.entity;

import com.cars.trip.onlinetrips.authentication.model.User;

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

    @OneToOne(mappedBy = "car")
    private User user;


    public Cars() {
    }

    public Cars(String carBrand, String countryOfOrigin, int enginePowerKW,User user) {
        this.carBrand = carBrand;
        this.countryOfOrigin = countryOfOrigin;
        this.enginePowerKW = enginePowerKW;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public int getEnginePowerKW() {
        return enginePowerKW;
    }

    public void setEnginePowerKW(int enginePowerKW) {
        this.enginePowerKW = enginePowerKW;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
