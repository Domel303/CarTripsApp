package com.cars.trip.onlinetrips.repository;


import com.cars.trip.onlinetrips.entity.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Cars,Long> {


}
