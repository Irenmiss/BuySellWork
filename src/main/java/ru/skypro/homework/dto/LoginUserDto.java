package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.skypro.homework.Enums.Role;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoginUserDto {

    private String username;
    private String password;
    private final Integer userId;
    private final Role role;
}
