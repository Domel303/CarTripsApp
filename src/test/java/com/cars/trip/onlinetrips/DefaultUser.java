//package com.cars.trip.onlinetrips;
//
//import com.cars.trip.onlinetrips.authentication.model.Role;
//import com.cars.trip.onlinetrips.authentication.model.RoleName;
//import com.cars.trip.onlinetrips.authentication.model.User;
//import com.cars.trip.onlinetrips.authentication.repository.RoleRepository;
//import com.cars.trip.onlinetrips.authentication.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//@Component
//public class DefaultUser {
//
//    private final String USERNAME = "Dominik";
//    private final String PASSWORD = "123456789";
//    private final String EMAIL = "email@email.com";
//
//private RoleRepository roleRepository;
//
//private UserRepository userRepository;
//
//private PasswordEncoder passwordEncoder;
//
//@Bean
//    CommandLineRunner addDefaultUser(){
//    return CommandLineRunner{
//        for (RoleName roleName : List.of(RoleName.ROLE_USER, RoleName.ROLE_ADMIN)) {
//            if (!roleRepository.existsByName(roleName)){
//                roleRepository.save( new Role(roleName));
//            }
//        }
//
//        Optional<User> user = userRepository.findByUsername(USERNAME);
//        if (!user.isPresent()){
//            User temp = new User("Dominik",
//                    "Simacek",
//                    USERNAME,
//                    EMAIL,
//                    passwordEncoder.encode(PASSWORD));
//            temp.setRoles(Set.of(new Role(RoleName.ROLE_ADMIN)));
//            userRepository.save(temp);
//        }
//    };
//}
//
//}