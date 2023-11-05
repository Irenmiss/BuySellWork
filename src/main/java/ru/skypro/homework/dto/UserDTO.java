package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.homework.Enums.Role;

/**
 * Data Transfer Object для получения полной информации о пользователе
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String image;
    private Role role;
}
