package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.entity.Cars;
import com.cars.trip.onlinetrips.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/userCar")
    public Cars getUserCar(@RequestParam String userName){
        return userService.getCar(userName);
    }
}
