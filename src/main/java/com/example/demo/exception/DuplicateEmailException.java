package com.example.demo.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException() {
        super("Email already exists");
    }

    public DuplicateEmailException(String email) {
        super("Email already exists: " + email);
    }

    public DuplicateEmailException(String email, Throwable cause) {
        super("Email already exists: " + email, cause);
    }
}
