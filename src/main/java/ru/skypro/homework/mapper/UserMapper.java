package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.dto.UpdateUserInfoDto;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.Image;
import ru.skypro.homework.entity.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "image", source = "image", qualifiedByName = "imageToPathString")
    UserDTO toUserDTO(User user);
    @Named("imageToPathString")
    default String imageToPathString(Image image) {
        if (image == null) {
            return null;
        }
        return "/users/image/" + image.getId();
    }
    User toUserEntity(RegisterUserDto registerUserDto);

    UpdateUserInfoDto toUpdateUserInfoDto(User user);

}

