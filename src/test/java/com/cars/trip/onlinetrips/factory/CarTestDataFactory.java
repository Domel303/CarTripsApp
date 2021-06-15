package com.cars.trip.onlinetrips.factory;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.entity.Car;
import com.cars.trip.onlinetrips.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarTestDataFactory {

    @Autowired
    CarRepository carRepository;

    public Car addCar(){
        Car car = new Car();
        car.setCarBrand("brand");
        car.setCarModel("model");
        car.setCountryOfOrigin("country");
        car.setEnginePowerKW("power");
        car.setUser(new User("firstname","lastname","username","email@email.cz","password"));

        carRepository.save(car);
        return car;
    }
}
