package ru.skypro.homework.mapper;

import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;

public interface UsersMapper {
    //    @Mapping(target = "image", source = "image", qualifiedByName = "imageToPathString")
    //    @Mapping(target = "image", source = "image")
    //    UserDTO toUserDTO(User user);
    ////    @Named("imageToPathString")
    ////    default String imageToPathString(Image image) {
    ////        if (image == null) {
    ////            return null;
    ////        }
    ////        return "/users/image/" + image.getId();
    ////    }
    //    User toUserEntity(RegisterUserDto registerUserDto);
    //
    //    UpdateUserInfoDto toUpdateUserInfoDto(User user);
    User toUserEntity(RegisterUserDto dto);

    UserDTO toUserDTO(User entity);
}
