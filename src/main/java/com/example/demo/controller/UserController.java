package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Management", description = "Endpoints for user authentication and account management")
@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Create new user", description = "Create a new user account")
    @PostMapping("/register")
    public ResponseEntity<Void> createUser(@Valid @RequestBody UserDto user) {
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Update user password", description = "Updates the password for an existing user")
    @PutMapping("/{username}/password")
    public ResponseEntity<Void> updatePassword(
            @Parameter(description = "Username of the account", required = true)
            @PathVariable String username,
            @Parameter(description = "New password", required = true)
            @RequestBody String newPassword) {
        userService.updatePassword(username, newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Find user by username", description = "Find user by username")
    @GetMapping("/{username}")
    public ResponseEntity<UserDto> findUser(
            @Parameter(description = "Username to find", required = true)
            @PathVariable String username) {
        return userService.findUser(username)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Validate user credentials", description = "Validate user credentials")
    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateUser(
            @Parameter(description = "User credentials to validate", required = true)
            @RequestBody UserDto user) {
        boolean isValid = userService.validateUser(user.getUserName(), user.getPassword());
        return new ResponseEntity<>(isValid, HttpStatus.OK);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleExceptions(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
