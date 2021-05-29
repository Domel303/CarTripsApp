package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.entity.Cars;

public interface UserService {
    public Cars getCar(String userName);
    public User getUserByUsername(String userName);
}
