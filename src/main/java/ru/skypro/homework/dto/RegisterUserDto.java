package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class RegisterUserDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private RoleDto role;

    public String getUsername() {
        return null;
    }

    public String getPassword() {
        return null;
    }

    public <E> Enum<E> getRole() {
        return null;
    }
}
