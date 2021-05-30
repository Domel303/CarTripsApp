package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.entity.Cars;
import com.cars.trip.onlinetrips.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userCar/{userName}")
    public Cars getUserCar(@PathVariable("userName") String userName){
        return userService.getCar(userName);
    }
}
