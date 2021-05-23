package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CarController {

    @Autowired
    CarService carService;



    @RequestMapping("/")
    public String greet(){
        return "Greetings from CarController";
    }
}
