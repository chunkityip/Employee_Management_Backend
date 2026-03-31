package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.dto.UserDto;
import com.example.demo.exception.DuplicateUsernameException;
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

    @Autowired
    private PasswordEncoder passwordEncoder; // ✅ inject PasswordEncoder

    @Override
    public void createUser(UserDto user) {
        checkUser(user);
        if (userDao.existsByUsername(user.getUserName())) {
            throw new DuplicateUsernameException("Username already exists: " + user.getUserName());
        }
        // ✅ Hash password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.insertUser(user);
    }

    @Override
    public Optional<UserDto> findUser(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public boolean validateUser(String username, String password) {
        Optional<UserDto> user = userDao.findByUsername(username);
        
        if (user.isEmpty()) {
            System.out.println("DEBUG: User not found in database: " + username);
            return false;
        }
        
        UserDto foundUser = user.get();
        String storedPassword = foundUser.getPassword();
        
        // ✅ Use BCrypt matcher for comparing passwords
        boolean matches = passwordEncoder.matches(password, storedPassword);
        
        System.out.println("DEBUG: User found: " + username);
        System.out.println("DEBUG: Provided password: " + (password != null ? "***" : "null"));
        System.out.println("DEBUG: Stored password hash: " + (storedPassword != null ? storedPassword.substring(0, Math.min(15, storedPassword.length())) + "..." : "null"));
        System.out.println("DEBUG: Password match result: " + matches);
        
        return matches;
    }

    @Override
    public void updatePassword(String username, String newPassword) {
        if (newPassword == null || newPassword.length() < 8) {
            throw new ValidationException("Password must be at least 8 characters");
        }
        // ✅ Hash password before updating
        String encodedPassword = passwordEncoder.encode(newPassword);
        userDao.updatePassword(username, encodedPassword);
    }

    private void checkUser(UserDto user) throws ValidationException {
        if (user.getUserName() == null || user.getUserName().trim().isEmpty()) {
            throw new ValidationException("Username cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().length() < 8) {
            throw new ValidationException("Password must be at least 8 characters");
        }
    }
}