package com.cars.trip.onlinetrips.factory;

import com.cars.trip.onlinetrips.authentication.model.Role;
import com.cars.trip.onlinetrips.authentication.model.RoleName;
import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.authentication.repository.UserRepository;
import com.cars.trip.onlinetrips.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserTestDataFactory {

    @Autowired
    private UserRepository userRepository;

    public User addUser() {
        User user = new User();
        user.setFirstname("firstname");
        user.setLastname("lastname");
        user.setUsername("username");
        user.setEmail("email@email.cz");
        user.setPassword("password");
        user.setCar(new Car("brand","model","country","power"));
        user.setRoles(Set.of(new Role(RoleName.ROLE_USER)));

        userRepository.save(user);
        return user;
    }


}
