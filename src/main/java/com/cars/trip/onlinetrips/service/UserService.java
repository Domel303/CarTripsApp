package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.entity.Car;
import org.springframework.data.domain.Page;

public interface UserService {
    public Car getCar(String userName);
    public User getUserByUsername(String userName);

    Page<User> getAllUsers(int page, int size);
}
