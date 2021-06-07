package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    List<Car> getAllCars();


    void addNewCar(Car car);

    void deleteCar(Long id);

    void updateCar(Long carId, String carBrand, String carModel, String countryOfOrigin, String enginePowerKw);
}
