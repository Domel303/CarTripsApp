package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.authentication.repository.UserRepository;
import com.cars.trip.onlinetrips.authentication.security.services.UserPrinciple;
import com.cars.trip.onlinetrips.dto.CarsDTO;
import com.cars.trip.onlinetrips.entity.Car;
import com.cars.trip.onlinetrips.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;


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
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public void createNewCar(@RequestBody CarsDTO userCarDTO) {
        UserPrinciple principles = (UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(principles.getUsername()).orElseThrow(() -> new IllegalStateException("User with name" + userCarDTO.getUser() + "could not be found"));
        userCarDTO.setUser(user);
        Car car = carService.addNewCar(userCarDTO);
        user.setCar(car);
        userRepository.save(user);
    }

    @DeleteMapping(path = "/{carId}")
    public void deleteCar(@PathVariable("carId") Long id) {
        carService.deleteCar(id);
    }

    @PutMapping(path = "/update")
    public void updateCar(@RequestBody CarsDTO carsDTO) {
        carService.updateCar(carsDTO);
    }
}
