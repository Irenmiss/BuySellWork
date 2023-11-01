package ru.skypro.homework.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.SetNewPasswordDto;
import ru.skypro.homework.dto.UpdateUserInfoDto;
import ru.skypro.homework.dto.UserDTO;


public interface UserService {

    UserDTO getMyInfo(String username);

    @Transactional
    UserDTO updateUser(UpdateUserInfoDto updateUserInfoDto, String username);


    boolean updateAvatar(String username, MultipartFile image);

    @Transactional
    boolean setPassword(SetNewPasswordDto password, String username);


    UserDTO getUser(String username);

}
