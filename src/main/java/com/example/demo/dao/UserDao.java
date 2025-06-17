package com.example.demo.dao;

import com.example.demo.dto.UserDto;

import java.util.Optional;

public interface UserDao {
    Optional<UserDto> findByUsername(String username);
    boolean existsByUsername(String username);
    void insertUser(UserDto user);
    void updatePassword(String username, String newPassword);
    void deleteByUsername(String username);
}
