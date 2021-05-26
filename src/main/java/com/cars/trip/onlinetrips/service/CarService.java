package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.entity.Cars;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    void createCar(Cars car);
    //public abstract List<Cars> getAllCars();
}
