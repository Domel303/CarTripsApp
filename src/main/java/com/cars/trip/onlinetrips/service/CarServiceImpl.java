package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.entity.Cars;
import com.cars.trip.onlinetrips.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    CarRepository carRepository;
    @Override
    public void createCar(Cars car) {
    //carRepository.insertCar(car);
    }

//    @Override
//    public List<Cars> getAllCars() {
//        List<Cars> allCars;
//        allCars = carRepository.getAllByCarBrand();
//        return allCars;
//    }

}
