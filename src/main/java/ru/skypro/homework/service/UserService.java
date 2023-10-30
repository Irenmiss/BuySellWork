package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.SetNewPasswordDto;
import ru.skypro.homework.dto.UpdateUserInfoDto;
import ru.skypro.homework.dto.UserDTO;

import java.io.IOException;


public interface UserService {

    UserDTO getMyInfo(String username);

    UpdateUserInfoDto updateUser(UpdateUserInfoDto updateUserInfoDto, String username);

    void updateAvatar(String username, MultipartFile image) throws IOException;

    boolean setPassword(SetNewPasswordDto password, String username);
}
