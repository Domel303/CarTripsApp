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
    public void updateCar(@RequestBody Cars car) {
        carService.updateCar(car.getId(), car.getCarBrand(), car.getCarModel(), car.getCountryOfOrigin(), car.getEnginePowerKW());
    }
}
