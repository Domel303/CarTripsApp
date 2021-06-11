package com.cars.trip.onlinetrips.repository;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.entity.AppEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<AppEvent, Long> {

    Optional<AppEvent> findById(Long id);

    //@Query(value = "Select * from singed_users_into_event u where u.user_id = ?1", nativeQuery = true)
    List<AppEvent> findAllBySingedUsers(User user);
}
