package ru.skypro.homework.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.SetNewPasswordDto;
import ru.skypro.homework.dto.UpdateUserInfoDto;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exceptions.NotFoundEntityException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
@Data
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private UsersRepository usersRepository;
    private UserMapper userMapper;
    private ImageService imageService;
    private PasswordEncoder encoder;

    @Override
    public UserDTO getMyInfo(String username) {
        User user = usersRepository.findByUsername(username);
        if (user != null) {
            return userMapper.toUserDTO(user);
        } else {
            throw new NotFoundEntityException("User not found");
        }
    }

    @Override
    public UpdateUserInfoDto updateUser(UpdateUserInfoDto updateUserInfoDto, String username) {

        User user = usersRepository.findByUsername(username);
        user.setFirstName(updateUserInfoDto.getFirstName());
        user.setLastName(updateUserInfoDto.getLastName());
        user.setPhone(updateUserInfoDto.getPhone());
        user.setImage(user.getImage());
        usersRepository.save(user);
        return userMapper.toUpdateUserInfoDto(user);
    }

    @Override
    public void updateAvatar(String username, MultipartFile image) throws IOException {
        User user = usersRepository.findByUsername(username);
        if (user != null) {
            user.setImage(imageService.upload(image));
            usersRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public boolean setPassword(SetNewPasswordDto password, String username) {

        User user = usersRepository.findByUsername(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        if (encoder.matches(password.getCurrentPassword(), user.getPassword())) {
            user.setPassword(encoder.encode(password.getNewPassword()));
            usersRepository.save(user);

            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

    }
}
