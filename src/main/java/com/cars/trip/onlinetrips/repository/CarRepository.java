package com.cars.trip.onlinetrips.repository;


import com.cars.trip.onlinetrips.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    boolean existsByCarBrand(String carBrand);
}
