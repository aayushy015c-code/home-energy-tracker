package com.aayush.user_service.service;

import com.aayush.user_service.dto.UserDTO;
import com.aayush.user_service.entity.User;
import com.aayush.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


// business logic
@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO) {
        //simulate user creation logic
        log.info("Creating user: {}", userDTO);

        final User createdUser = User.builder()
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .alerting(userDTO.isAlerting())
                .energyAlertingThreshold(userDTO.getEnergyAlertingThreshold())
                .build();

        User saved = userRepository.save(createdUser);

        return toDTO(saved);
    }

    private UserDTO toDTO(User saved) {
        return UserDTO.builder()
                .name(saved.getName())
                .surname(saved.getSurname())
                .email(saved.getEmail())
                .address(saved.getAddress())
                .alerting(saved.isAlerting())
                .energyAlertingThreshold(saved.getEnergyAlertingThreshold())
                .build();
    }

}
