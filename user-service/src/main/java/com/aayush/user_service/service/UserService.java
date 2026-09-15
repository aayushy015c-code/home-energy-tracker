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

    public UserDTO getUserById(Long id) {

        return userRepository.findById(id)
                .map(this::toDTO) // maps the Optional returned by user repo to User object to which futher is mapped to UserDTO
                .orElse(null);
    }

    public void updateUser(Long id, UserDTO userDTO) {

        // Optinal Mapped To User
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        //updating existing user record with updated fields which we got from requestbody
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setAddress(userDTO.getAddress());
        user.setAlerting(userDTO.isAlerting()); // boolean
        user.setEnergyAlertingThreshold(userDTO.getEnergyAlertingThreshold());

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        //finding user to delete
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User Not Found"));

        userRepository.delete(user);
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
