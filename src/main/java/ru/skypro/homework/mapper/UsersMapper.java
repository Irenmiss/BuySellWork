package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.entity.User;

/**
 * Настройка маппинга для преобразования сущностей пользователей в DTO и обратно
 */

public interface UsersMapper {

    User toUserEntity(RegisterUserDto dto);

    UserDTO toUserDTO(User entity);
}
