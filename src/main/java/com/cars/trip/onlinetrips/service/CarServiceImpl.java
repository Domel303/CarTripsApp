package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.entity.Car;
import com.cars.trip.onlinetrips.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;


@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    @Override
    public void addNewCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void deleteCar(Long id) {
        boolean exist = carRepository.existsById(id);
        if (!exist) {
            throw new IllegalStateException("Car doesn´t exists");
        }
        carRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateCar(Long carId, String carBrand, String carModel, String countryOfOrigin, String enginePowerKw) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new IllegalStateException("Car with id" + carId + "could not be found"));

        if (carBrand != null && carBrand.length() > 0 && !Objects.equals(car.getCarBrand(), carBrand)) {
            car.setCarBrand(carBrand);
        }

        if (carModel != null && carModel.length() > 0 && !Objects.equals(car.getCarModel(), carModel)) {
            car.setCarModel(carModel);
        }

        if (countryOfOrigin != null && countryOfOrigin.length() > 0 && !Objects.equals(car.getCountryOfOrigin(), countryOfOrigin)) {
            car.setCountryOfOrigin(countryOfOrigin);
        }

        if (enginePowerKw != null && enginePowerKw.length() > 0 && !Objects.equals(car.getEnginePowerKW(), enginePowerKw)) {
            car.setEnginePowerKW(enginePowerKw);
        }
    }

}
