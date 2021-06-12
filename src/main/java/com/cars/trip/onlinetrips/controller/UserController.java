package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.authentication.security.services.UserPrinciple;
import com.cars.trip.onlinetrips.entity.Car;
import com.cars.trip.onlinetrips.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Page<User> getAllUsers(@RequestParam(required = false) int page,
                                  @RequestParam(required = false) int size) {
        return userService.getAllUsers(page, size);
    }

    @GetMapping("/car")
    public Car getMyCar(){
        UserPrinciple principles = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getCar(principles.getUsername());
    }

    @GetMapping("/{userName}")
    public Car getUserCar(@PathVariable("userName") String userName) {
        return userService.getCar(userName);
    }



}
