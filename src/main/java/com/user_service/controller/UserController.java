package com.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.user_service.entities.User;
import com.user_service.service.UserService;

@RestController
@RequestMapping("/users")
@Tag(name = "Api Rest for brands use Swagger 3 - User")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @Operation(summary = "This endpoint is used for register a new user")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return ResponseEntity.ok("Registration successful");
        }catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "This endpoint is to find a user")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping
    @Operation(summary = "This endpoint is to find all users")
    public ResponseEntity<?> getUser(){
        return ResponseEntity.ok(userService.findAll());
    }
}
