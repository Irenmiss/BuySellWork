package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.skypro.homework.Enums.Role;

@AllArgsConstructor
@Data
public class RegisterUserDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

}
