package com.aayush.user_service.controller;

import com.aayush.user_service.dto.UserDTO;
import com.aayush.user_service.service.UserService;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO created = userService.createUser(userDTO);

        //return new ResponseEntity<>(created, HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);

        if(userDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{id}")
    // can also have returned updated UserDTO, but here we returned a String
    public ResponseEntity<String> updateUser(@PathVariable Long id, // dont forget comma
                                             @RequestBody UserDTO userDTO) {

        try {
            userService.updateUser(id, userDTO);
            return ResponseEntity.ok("User updated successfully");
        } catch (IllegalArgumentException e) { // is user is not found
            return new ResponseEntity<>("User Not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build(); // returning a response entity with no content or body
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
