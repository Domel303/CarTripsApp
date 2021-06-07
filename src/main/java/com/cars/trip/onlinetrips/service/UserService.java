package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.entity.Car;

public interface UserService {
    public Car getCar(String userName);
    public User getUserByUsername(String userName);
}
