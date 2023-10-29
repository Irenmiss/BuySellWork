package ru.skypro.homework.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUserInfoDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

@Service
@Data
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private UsersRepository usersRepository;
    private UserMapper userMapper;
    ImageService imageService;

    @Override
    public void newPassword(String newPassword, String currentPassword) {
    }

    @Override
    public User findUsername(Authentication authentication) {
        return usersRepository.findByUsername(authentication.getName());
    }
    @Override
    public UpdateUserInfoDto updateUser(UpdateUserInfoDto updateUserInfoDto) {
        User currentUser = findUser();
        User updatedtUser = new User();
        if (currentUser != null) {
//            updatedtUser = currentUser.get();
            updatedtUser.setFirstName(updateUserInfoDto.getFirstName());
            updatedtUser.setLastName(updateUserInfoDto.getLastName());
            updatedtUser.setPhone(updateUserInfoDto.getPhone());
            usersRepository.save(updatedtUser);
        }
        return userMapper.toUpdateUserInfoDto(updatedtUser);
    }
    @Override
    public User findUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return usersRepository.findByUsername(currentPrincipalName);
    }
//    @Override
//    public boolean updateUserImage(String username, MultipartFile image) {
//
//        String imageId = imageService.addImage(image);
//        User user = usersRepository.findByUsername(username);
//        user.setImage(imageId);
//        usersRepository.save(user);
//
//        return true;
//    }
}
