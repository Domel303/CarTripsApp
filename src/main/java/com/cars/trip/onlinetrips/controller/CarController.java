package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.entity.Cars;
import com.cars.trip.onlinetrips.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping("/")
    public String greet(){
        return "Greetings from CarController";
    }

    @GetMapping("/allCars")
    public List<Cars> getAllCars(){
        return carService.getAllCars();
    }

    @PostMapping
    public void createNewCar(@RequestBody Cars car){
        carService.addNewCar(car);
    }

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
