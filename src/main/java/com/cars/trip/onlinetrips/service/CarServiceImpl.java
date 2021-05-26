package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.entity.Cars;
import com.cars.trip.onlinetrips.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CarServiceImpl implements CarService{

    @Autowired
    CarRepository carRepository;

    @Override
    public void createCar(Cars car) {
    carRepository.save(car);
    }



}
