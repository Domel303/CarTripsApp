package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.authentication.repository.UserRepository;
import com.cars.trip.onlinetrips.entity.Cars;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Cars getCar(String userName){
        User user = userRepository.findByUsername(userName).orElseThrow(()-> new IllegalStateException("User " + userName+ "could not be found"));
        return user.getCar();
    }

    public User getUserByUsername(String userName){
        return userRepository.findByUsername(userName).orElseThrow(()-> new IllegalStateException("User " + userName+ "could not be found"));
    }
}
