package ru.skypro.homework.service;

/**
 * Сервис по валидации сущностей
 */
public interface ValidationService {
    /**
     * Валидация сущности
     * {@link ru.skypro.homework.service.impl.ValidationServiceImpl#validate(Object)}
     *
     * @param object валидируемая сущность
     * @return валидная сущность.
     */
    boolean validate(Object object);
}
