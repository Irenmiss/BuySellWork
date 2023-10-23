package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.dto.UpdateUserInfoDto;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "email", source = "username")
    User toUserEntity(RegisterUserDto registerUserDto);
    @Mapping(target = "password", ignore = true)
    User toUser(UserDTO userDTO);
    UserDTO toUserDTO(User user);
    @Mapping (target = "id", ignore = true)
    @Mapping(target = "email", source = "username")
    @Mapping(target = "role", defaultValue = "USER")
    User toUser(RegisterUserDto registerUserDto);
    UpdateUserInfoDto toUpdateUserInfo(User user);

}
