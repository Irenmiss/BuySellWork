package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;

public interface UsersMapper {

    User toUserEntity(RegisterUserDto dto);

    UserDTO toUserDTO(User entity);
}
