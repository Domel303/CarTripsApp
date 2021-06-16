package com.cars.trip.onlinetrips.controller;

import com.cars.trip.onlinetrips.OnlineTripsApplication;
import com.cars.trip.onlinetrips.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = OnlineTripsApplication.class)
@AutoConfigureMockMvc
class AuthRestAPIsTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EventRepository eventRepository;

    @BeforeEach
    public void setMockMvc(){
        assertNotNull(mockMvc);
    }

    @Test
    public void logInSuccesfully() throws Exception {

//return unauthorized
        
        MvcResult response = mockMvc.perform(post("/api/auth/signin")
                .content("{\"username\":\"Dominik\",\"password\":\"123456789\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String result =response.getResponse().getContentAsString();
        assertTrue(result.contains("accessToken"));
    }

    @Test
    public void logInUnSuccesfully() throws Exception {


        MvcResult response = mockMvc.perform(post("/api/auth/signin")
                .content("{\"username\":\"Stranger\",\"password\":\"123456789\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        String result =response.getResponse().getContentAsString();
        assertFalse(result.contains("accessToken"));
    }

}