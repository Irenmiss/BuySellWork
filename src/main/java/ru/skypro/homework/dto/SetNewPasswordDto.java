package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class SetNewPasswordDto {
    private String currentPassword;
    private String newPassword;
}

