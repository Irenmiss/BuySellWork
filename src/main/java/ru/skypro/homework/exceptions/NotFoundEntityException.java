package ru.skypro.homework.exceptions;

/**
 * Обработка исключения, возникающего в случае попытки обращения к несуществующей сущности
 */
public class NotFoundEntityException extends RuntimeException {
    public NotFoundEntityException(String message) {
        super(message);
    }
}
