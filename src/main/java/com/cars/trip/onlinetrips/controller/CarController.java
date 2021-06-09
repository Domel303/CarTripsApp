package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.authentication.repository.UserRepository;
import com.cars.trip.onlinetrips.authentication.security.services.UserPrinciple;
import com.cars.trip.onlinetrips.dto.CarsDTO;
import com.cars.trip.onlinetrips.entity.Car;
import com.cars.trip.onlinetrips.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;




@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;
    private final UserRepository userRepository;

    public CarController(CarService carService, UserRepository userRepository) {
        this.carService = carService;
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/")
    public Page<Car> getAllCars(@RequestParam(required = false) int page,
                                @RequestParam(required = false) int size) {
        return carService.getAllCars(page,size);
    }


    @PostMapping
    public Car createNewCar(@RequestBody CarsDTO userCarDTO) {
        Object principles = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user;
        if(principles instanceof UserPrinciple)
            user = userRepository.findByUsername(((UserPrinciple)principles).getUsername()).orElseThrow(() -> new IllegalStateException("User with name" + userCarDTO.getUser() + "could not be found"));
        else
            user = userRepository.findByUsername(principles.toString()).orElseThrow(() -> new IllegalStateException("User with name " + principles.toString() + "could not be found"));
        userCarDTO.setUser(user);
        Car car = carService.addNewCar(userCarDTO);
        user.setCar(car);
        userRepository.save(user);
        return car;
    }

    @DeleteMapping(path = "/{carId}")
    public void deleteCar(@PathVariable("carId") Long id) {
        carService.deleteCar(id);
    }

    @PutMapping(path = "/")
    public void updateCar(@RequestBody CarsDTO carsDTO) {
        carService.updateCar(carsDTO);
    }
}
