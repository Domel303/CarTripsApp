package com.cars.trip.onlinetrips.request;

import com.cars.trip.onlinetrips.entity.Cars;

public class UserCar {
    String userName;
    Cars car;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Cars getCar() {
        return car;
    }

    public void setCar(Cars car) {
        this.car = car;
    }
}
