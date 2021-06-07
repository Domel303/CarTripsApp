package com.cars.trip.onlinetrips.dto;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.OneToOne;

public class CarsDTO {
    private Long id;

    private String carBrand;

    private String carModel;

    private String countryOfOrigin;

    private String enginePowerKW;

    private User user;

    public CarsDTO() {
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

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getEnginePowerKW() {
        return enginePowerKW;
    }

    public void setEnginePowerKW(String enginePowerKW) {
        this.enginePowerKW = enginePowerKW;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
