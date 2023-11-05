package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterUserDto;

/**
 * Сервис по регистрации и аутентификации пользователей
 */

public interface AuthService {
    /**
     * Авторизация пользователя
     * {@link ru.skypro.homework.service.impl.AuthServiceImpl#login(String, String)}
     *
     * @param userName имя пользователя
     * @param password пароль пользователя
     * @return true or false
     */

    boolean login(String userName, String password);

    /**
     * Регистрация пользователя
     * {@link ru.skypro.homework.service.impl.AuthServiceImpl#register(RegisterUserDto)} (String, String)}
     *
     * @param register объект DTO с регистрационными данными
     * @return true or false
     */
    boolean register(RegisterUserDto register);
}
