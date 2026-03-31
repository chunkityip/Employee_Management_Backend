package com.example.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Utility class to generate BCrypt password hashes for existing plain text passwords.
 * Run this as a standalone Java application or Spring Boot app to generate hashes.
 * 
 * Usage:
 * 1. Enter your plain text password when prompted
 * 2. Copy the generated hash
 * 3. Run SQL: UPDATE user SET password = '[copied-hash]' WHERE username = 'username';
 */
public class PasswordHashGenerator {

    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // Example passwords to hash
        String[] testPasswords = {
            "testPassword123",
            "hrtest123",
            "admin12345",
            "user@123456"
        };
        
        System.out.println("=== BCrypt Password Hash Generator ===\n");
        System.out.println("Copy these hashes to use in your database UPDATE statements:\n");
        
        for (String password : testPasswords) {
            String hash = encoder.encode(password);
            System.out.println("Original: " + password);
            System.out.println("Hash:     " + hash);
            System.out.println("---");
        }
        
        // Interactive mode - uncomment to use
        /*
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nEnter a password to hash (or 'exit' to quit):");
        while (true) {
            String input = scanner.nextLine().trim();
            if ("exit".equalsIgnoreCase(input)) {
                break;
            }
            if (!input.isEmpty()) {
                String hash = encoder.encode(input);
                System.out.println("Hash: " + hash);
                System.out.println("\nEnter next password (or 'exit' to quit):");
            }
        }
        scanner.close();
        */
    }
}

