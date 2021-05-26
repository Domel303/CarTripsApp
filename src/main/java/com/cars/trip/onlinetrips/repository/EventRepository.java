package com.cars.trip.onlinetrips.repository;

import com.cars.trip.onlinetrips.entity.AppEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<AppEvents, Long> {
}
