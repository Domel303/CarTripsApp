package com.cars.trip.onlinetrips.request;

import com.cars.trip.onlinetrips.entity.Car;

public class UserCar {
    String userName;
    Car car;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
