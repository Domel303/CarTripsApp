package com.cars.trip.onlinetrips.repository;


import com.cars.trip.onlinetrips.entity.Cars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Cars, Long> {

}
