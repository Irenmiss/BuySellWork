package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Data Transfer Object для аутентификации пользователей
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginUserDto {

    private String username;
    private String password;
}
