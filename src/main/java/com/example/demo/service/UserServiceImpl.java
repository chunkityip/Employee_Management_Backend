package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.DuplicateUsernameException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void createUser(UserDto user) {
        checkUser(user);
        if (userDao.existsByUsername(user.getUserName())) {
            throw new DuplicateUsernameException("Username already exists: " + user.getUserName());
        }
        user.setPassword(user.getPassword());
        userDao.insertUser(user);
    }

    @Override
    public void updatePassword(String username, String newPassword) {
        if (!userDao.existsByUsername(username)) {
            throw new EntityNotFoundException("User not found: " + username);
        }
        if (newPassword == null || newPassword.length() < 8) {
            throw new ValidationException("Password must be at least 8 characters");
        }
        userDao.updatePassword(username, newPassword);
    }

    @Override
    public Optional<UserDto> findUser(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public boolean validateUser(String username, String password) {
        Optional<UserDto> user = userDao.findByUsername(username);
        return user.map(u -> password.equals(u.getPassword()))
                .orElse(false);
    }

    private void checkUser(UserDto user) {
        if (user.getUserName() == null || user.getUserName().trim().isEmpty()) {
            throw new ValidationException("Username cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().length() < 8) {
            throw new ValidationException("Password must be at least 8 characters");
        }
    }
}
