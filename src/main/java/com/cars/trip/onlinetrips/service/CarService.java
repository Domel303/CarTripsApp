package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.dto.CarsDTO;
import com.cars.trip.onlinetrips.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    List<Car> getAllCars();


    Car addNewCar(CarsDTO car);

    void deleteCar(Long id);

    void updateCar(CarsDTO carsDTO);
}
