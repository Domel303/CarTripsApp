package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.dto.CarsDTO;
import com.cars.trip.onlinetrips.entity.Car;
import com.cars.trip.onlinetrips.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Page<Car> getAllCars(int page, int size) {
        if (!(page < 0 ||size <= 0)){
            PageRequest onPage= PageRequest.of(page, size);
            return carRepository.findAll(onPage);
        }else{
            return Page.empty();
        }
    }


    @Override
    public Car addNewCar(CarsDTO carDTO) {
        Car car = mapCarDTOToCar(carDTO);
        return carRepository.save(car);
    }

    private Car mapCarDTOToCar(CarsDTO carDTO) {
        Car car = new Car();
        car.setCarBrand(carDTO.getCarBrand());
        car.setCarModel(carDTO.getCarModel());
        car.setCountryOfOrigin(carDTO.getCountryOfOrigin());
        car.setEnginePowerKW(carDTO.getEnginePowerKW());
        car.setUser(carDTO.getUser());

        return car;
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
    public void updateCar(CarsDTO carsDTO) {
        Car car = carRepository.findById(carsDTO.getId()).orElseThrow(() -> new IllegalStateException("Car with id" + carsDTO.getId() + "could not be found"));

        if (carsDTO.getCarBrand() != null && carsDTO.getCarBrand().length() > 0 && !Objects.equals(car.getCarBrand(), carsDTO.getCarBrand())) {
            car.setCarBrand(carsDTO.getCarBrand());
        }

        if (carsDTO.getCarModel() != null && carsDTO.getCarModel().length() > 0 && !Objects.equals(car.getCarModel(), carsDTO.getCarModel())) {
            car.setCarModel(carsDTO.getCarModel());
        }

        if (carsDTO.getCountryOfOrigin() != null && carsDTO.getCountryOfOrigin().length() > 0 && !Objects.equals(car.getCountryOfOrigin(), carsDTO.getCountryOfOrigin())) {
            car.setCountryOfOrigin(carsDTO.getCountryOfOrigin());
        }

        if (carsDTO.getEnginePowerKW() != null && carsDTO.getEnginePowerKW().length() > 0 && !Objects.equals(car.getEnginePowerKW(), carsDTO.getEnginePowerKW())) {
            car.setEnginePowerKW(carsDTO.getEnginePowerKW());
        }
    }

}
