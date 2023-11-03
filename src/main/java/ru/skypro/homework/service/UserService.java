package ru.skypro.homework.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.SetNewPasswordDto;
import ru.skypro.homework.dto.UpdateUserInfoDto;
import ru.skypro.homework.dto.UserDTO;

/**
 * Сервис для работы с пользователями
 */
public interface UserService {

    /**
     * Редактирование данных о пользователе
     *
     * @param updateUserInfoDto новые данные о пользователе в виде DTO
     * @param username          имя пользователя
     * @return измененная информация о пользователе
     */

    @Transactional
    UserDTO updateUser(UpdateUserInfoDto updateUserInfoDto, String username);

    /**
     * Изменение аватара
     *
     * @param username имя пользователя
     * @param image    новый аватар
     * @return true or false
     */

    boolean updateAvatar(String username, MultipartFile image);

    /**
     * Изменение пароля
     * {@link ru.skypro.homework.service.impl.UserServiceImpl#setPassword(SetNewPasswordDto, String)}
     *
     * @param password новый пароль
     * @param username информация о пользователе
     * @return true or false
     */

    @Transactional
    boolean setPassword(SetNewPasswordDto password, String username);

    /**
     * Просмотр информации об авторизованном пользователе
     * {@link ru.skypro.homework.service.impl.UserServiceImpl#getUser(String)}
     *
     * @param username имя пользователя
     * @return информация о пользователе
     */

    UserDTO getUser(String username);
}
