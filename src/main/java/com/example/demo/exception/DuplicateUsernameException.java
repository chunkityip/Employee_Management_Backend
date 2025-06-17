package com.example.demo.exception;

public class DuplicateUsernameException extends RuntimeException {

    public DuplicateUsernameException() {
        super("Username already exists");
    }

    public DuplicateUsernameException(String email) {
        super("Username already exists: " + email);
    }

    public DuplicateUsernameException(String username, Throwable cause) {
        super("Username already exists: " + username, cause);
    }
}
