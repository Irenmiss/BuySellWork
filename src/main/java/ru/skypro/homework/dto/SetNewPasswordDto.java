package ru.skypro.homework.dto;

import lombok.Data;

/**
 * Data Transfer Object для процесса изменения пароля пользователя
 */

@Data
public class SetNewPasswordDto {
    private String currentPassword;
    private String newPassword;
}

