package ru.skypro.homework.exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super("Validation failed");
    }
}
