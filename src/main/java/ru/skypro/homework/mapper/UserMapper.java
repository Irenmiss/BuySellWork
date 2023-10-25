package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.dto.UpdateUserInfoDto;
import ru.skypro.homework.entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "email", source = "username")
    @Mapping(target = "role", defaultValue = "USER")
    @Mapping (target = "id", ignore = true)
    User toUser(RegisterUserDto registerUserDto);
    RegisterUserDto toRegisterUser(User user);
    UpdateUserInfoDto toUpdateUserInfoDto(User user);
}
