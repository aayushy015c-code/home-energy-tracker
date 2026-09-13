package com.aayush.user_service.service;

import com.aayush.user_service.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


// business logic
@Slf4j
@Service
public class UserService {

    public UserDTO createUser(UserDTO userDTO) {
        //simulate user creation logic
        log.info("Creating user: {}", userDTO);
        return userDTO;
        // in real world, return the created user with ID
    }

}
