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
    public List<Cars> getAllCars(){
        return carService.getAllCars();
    }
    @GetMapping(path = "/allCars{userName}")
    public List<Cars> getAllCars(@PathVariable String userName){
        return carService.getAllUsersCars(userName);
    }

//    @PostMapping
//    public void createNewCar(@RequestParam String userName ,@RequestBody Cars car){
//        User user = userRepository.findByUsername(userName).orElseThrow(()-> new IllegalStateException("User with name"+ userName+ "could not be found"));
//        car.setUser(user);
//        carService.addNewCar(car);
//        System.out.println("car added with user " + userName);
//    }

    @DeleteMapping(path = "{carId}")
    public void deleteCar(@PathVariable("carId") Long id){
        carService.deleteCar(id);
    }

    @PutMapping(path = "{carId}")
    public void updateCar(
            @PathVariable("carId") Long carId,
            @RequestParam(required = false) String carBrand,
            @RequestParam(required = false) String carModel,
            @RequestParam(required = false) String countryOfOrigin,
            @RequestParam(required = false) String enginePowerKw){
        carService.updateCar(carId,carBrand,carModel,countryOfOrigin,enginePowerKw);
    }
}
