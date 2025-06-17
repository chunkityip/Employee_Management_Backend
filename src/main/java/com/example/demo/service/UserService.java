package com.example.demo.service;

import com.example.demo.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public interface UserService {
    void createUser(UserDto user);
    void updatePassword(String username, String newPassword);
    Optional<UserDto> findUser(String username);
    boolean validateUser(String username, String password);
}
