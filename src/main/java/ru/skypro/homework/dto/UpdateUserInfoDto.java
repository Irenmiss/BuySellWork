package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object для процесса изменения данных пользователя
 */

@Data
@AllArgsConstructor
public class UpdateUserInfoDto {
    private String firstName;

    private String lastName;

    private String phone;
}
