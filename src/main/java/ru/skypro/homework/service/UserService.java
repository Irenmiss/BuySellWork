package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUserInfoDto;
import ru.skypro.homework.entity.User;

import java.util.Optional;

public interface UserService {
    void newPassword (String newPassword , String currentPassword);

    User findUsername(Authentication authentication);

    UpdateUserInfoDto updateUser(UpdateUserInfoDto updateUserInfoDt);

    User findUser();

//    boolean updateUserImage(String username, MultipartFile image);
}
