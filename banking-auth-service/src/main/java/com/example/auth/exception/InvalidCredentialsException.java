// src/main/java/com/example/auth/exception/InvalidCredentialsException.java
package com.example.auth.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}