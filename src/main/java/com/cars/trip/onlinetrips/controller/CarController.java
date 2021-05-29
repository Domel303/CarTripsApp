package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.authentication.repository.UserRepository;
import com.cars.trip.onlinetrips.entity.Cars;
import com.cars.trip.onlinetrips.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;
    private final UserRepository userRepository;

    @Autowired
    public CarController(CarService carService, UserRepository userRepository) {
        this.carService = carService;
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/allCars")
    public List<Cars> getAllCars() {
        return carService.getAllCars();
    }


    @PostMapping
    public void createNewCar(@RequestParam String userName, @RequestBody Cars car) {
        User user = userRepository.findByUsername(userName).orElseThrow(() -> new IllegalStateException("User with name" + userName + "could not be found"));
        car.setUser(user);
        carService.addNewCar(car);
        user.setCar(car);
        userRepository.save(user);
        System.out.println("car added with user " + userName);
    }

    @PostMapping(path = "/delete")
    public void deleteCar(@RequestParam Long id) {
        carService.deleteCar(id);
    }

    @PutMapping(path = "/update")
    public void updateCar(
            @RequestParam(required = true) Long carId,
            @RequestParam(required = false) String carBrand,
            @RequestParam(required = false) String carModel,
            @RequestParam(required = false) String countryOfOrigin,
            @RequestParam(required = false) String enginePowerKw) {
        carService.updateCar(carId, carBrand, carModel, countryOfOrigin, enginePowerKw);
    }
}
