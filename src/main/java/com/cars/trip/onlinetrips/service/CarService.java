package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.dto.CarsDTO;
import com.cars.trip.onlinetrips.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarService {

    Page<Car> getAllCars(int page, int size);


    Car addNewCar(CarsDTO car);

    void deleteCar(Long id);

    void updateCar(CarsDTO carsDTO);
}
