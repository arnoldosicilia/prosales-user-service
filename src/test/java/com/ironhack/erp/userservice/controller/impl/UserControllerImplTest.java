package com.ironhack.erp.userservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.erp.userservice.model.entities.User;
import com.ironhack.erp.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerImplTest {

    @Autowired
    UserControllerImpl userController;

    @MockBean
    UserRepository userRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    User user;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        user = new User("admin", "admin",Long.valueOf(1) );

        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        when(userRepository.findById((long) 1)).thenReturn(java.util.Optional.ofNullable(user));
        when(userRepository.save(user)).thenReturn(user);
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/user/1")).andExpect(status().isOk());
    }

    @Test
    void findByUsername() throws Exception {
        mockMvc.perform(get("/username/admin")).andExpect(status().isOk());
    }

    @Test
    void createUser() throws Exception {
        mockMvc.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateUser() throws Exception{
        mockMvc.perform(put("/users/1")
                .content(objectMapper.writeValueAsString(user))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/users/1")).andExpect(status().isNoContent());
    }
}