package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.authentication.repository.UserRepository;
import com.cars.trip.onlinetrips.entity.Car;
import com.cars.trip.onlinetrips.request.UserCar;
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
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @PostMapping
    public void createNewCar(@RequestBody UserCar userCar) {
        User user = userRepository.findByUsername(userCar.getUserName()).orElseThrow(() -> new IllegalStateException("User with name" + userCar.getUserName() + "could not be found"));
        userCar.getCar().setUser(user);
        carService.addNewCar(userCar.getCar());
        user.setCar(userCar.getCar());
        userRepository.save(user);
    }

    @DeleteMapping(path = "/{carId}")
    public void deleteCar(@PathVariable("carId") Long id) {
        carService.deleteCar(id);
    }

    @PutMapping(path = "/update")
    public void updateCar(@RequestBody Car car) {
        carService.updateCar(car.getId(), car.getCarBrand(), car.getCarModel(), car.getCountryOfOrigin(), car.getEnginePowerKW());
    }
}
