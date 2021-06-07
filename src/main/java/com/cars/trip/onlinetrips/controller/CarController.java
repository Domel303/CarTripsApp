package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.authentication.repository.UserRepository;
import com.cars.trip.onlinetrips.authentication.security.services.UserPrinciple;
import com.cars.trip.onlinetrips.dto.CarsDTO;
import com.cars.trip.onlinetrips.entity.Car;
import com.cars.trip.onlinetrips.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Page<Car> getAllCars(@RequestParam(required = false) int page,
                                @RequestParam(required = false) int size) {
        return carService.getAllCars(page,size);
    }

    @GetMapping(path = "/allCarsNP")
    public List<Car> getAllCarList(){
        return carService.getAllCarsNP();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
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
