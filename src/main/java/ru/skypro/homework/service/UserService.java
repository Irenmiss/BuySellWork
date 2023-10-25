package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.UpdateUserInfoDto;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;

import java.util.Optional;

public interface UserService {
    void newPassword (String newPassword , String currentPassword);

    Optional<User> findUserByEmail(Authentication authentication);

    UpdateUserInfoDto updateUser(UpdateUserInfoDto updateUserInfoDt);

    Optional <User> findUser();
}
