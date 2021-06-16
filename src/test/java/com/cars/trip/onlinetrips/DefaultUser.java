package com.cars.trip.onlinetrips;

import com.cars.trip.onlinetrips.authentication.model.Role;
import com.cars.trip.onlinetrips.authentication.model.RoleName;
import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.authentication.repository.RoleRepository;
import com.cars.trip.onlinetrips.authentication.repository.UserRepository;
import com.cars.trip.onlinetrips.factory.Creator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class DefaultUser {

    private final String USERNAME = "Dominik";
    private final String PASSWORD = "123456789";

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    Creator creator;

    @Bean
    CommandLineRunner addDefaultUser() {
        CommandLineRunner cm = new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                for (RoleName roleName : List.of(RoleName.ROLE_USER, RoleName.ROLE_ADMIN)) {
                    if (!roleRepository.existsByName(roleName)) {
                        roleRepository.save(new Role(roleName));
                    }
                }

                Optional<User> user = userRepository.findByUsername(USERNAME);

                if (!user.isPresent()) {
                    User newUser = new User();
                    newUser.setUsername(USERNAME);
                    newUser.setPassword(passwordEncoder.encode(PASSWORD));
                    creator.saveEntity(newUser);


                }
            }
        };

        return cm;
    }


}
