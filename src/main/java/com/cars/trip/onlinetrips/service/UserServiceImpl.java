package com.cars.trip.onlinetrips.service;

import com.cars.trip.onlinetrips.authentication.model.User;
import com.cars.trip.onlinetrips.authentication.repository.UserRepository;
import com.cars.trip.onlinetrips.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Car getCar(String userName){
        User user = userRepository.findByUsername(userName).orElseThrow(()-> new IllegalStateException("User " + userName+ "could not be found"));
        return user.getCar();
    }

    public User getUserByUsername(String userName){
        return userRepository.findByUsername(userName).orElseThrow(()-> new IllegalStateException("User " + userName+ "could not be found"));
    }

    @Override
    public Page<User> getAllUsers(int page, int size) {
        if (!(page < 0 ||size <= 0)){
            PageRequest onPage= PageRequest.of(page, size);
            return userRepository.findAll(onPage);
        }else{
            return Page.empty();
        }
    }
}
