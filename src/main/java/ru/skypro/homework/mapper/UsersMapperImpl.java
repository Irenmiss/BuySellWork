package ru.skypro.homework.mapper;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;

//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Service
public class UsersMapperImpl implements UsersMapper {
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
    @Override
public User toUserEntity(RegisterUserDto dto){

    User entity = new User();
    entity.setFirstName(dto.getFirstName());
    entity.setLastName(dto.getLastName());
    entity.setUsername(dto.getUsername());
    entity.setPassword(dto.getPassword());
    entity.setPhone(dto.getPhone());
    entity.setRole(dto.getRole());

    return entity;
}
@Override
    public UserDTO toUserDTO(User entity) {

        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getUsername());
        dto.setPhone(entity.getPhone());

        if (entity.getImage() != null) {
            dto.setImage(String.format("/users/image/%s", entity.getImage()));
        } else {
            dto.setImage(null);
        }

        return dto;
    }

}

