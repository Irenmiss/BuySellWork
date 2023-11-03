package ru.skypro.homework.exceptions;

/**
 * Обработка исключения, возникающего при ошибках валидации
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super("Validation failed");
    }
}
