package com.cars.trip.onlinetrips.repository;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.entity.AppEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<AppEvent, Long> {

    Optional<AppEvent> findById(Long id);
    List<AppEvent> findAllBySingedUsers(Long id);
}
