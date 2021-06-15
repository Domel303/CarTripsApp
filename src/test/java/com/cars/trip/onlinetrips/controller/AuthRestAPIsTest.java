package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.OnlineTripsApplication;
import com.cars.trip.onlinetrips.authentication.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = OnlineTripsApplication.class)
@AutoConfigureMockMvc
class AuthRestAPIsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void setMockMvc(){
        assertNotNull(mockMvc);
    }

    @Test
    void authenticateUser() {
        //com.cars.trip.onlinetrips.controller unit test

    }

    @Test
    void authenticateUserWrongCredentials(){
        //com.cars.trip.onlinetrips.controller unit test
    }
}