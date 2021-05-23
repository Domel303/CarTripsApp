package com.cars.trip.onlinetrips.authentication.repository;


import com.cars.trip.onlinetrips.authentication.model.Role;
import com.cars.trip.onlinetrips.authentication.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}