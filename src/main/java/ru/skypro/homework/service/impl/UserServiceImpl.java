package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.SetNewPasswordDto;
import ru.skypro.homework.dto.UpdateUserInfoDto;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.entity.User;
import ru.skypro.homework.exceptions.NotFoundEntityException;
import ru.skypro.homework.mapper.UsersMapper;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

/**
 * Реализация бизнес-логики по работе с пользователями
 */

@Service
@Data
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UsersRepository usersRepository;
    private UsersMapper usersMapper;
    private ImageService imageService;
    private PasswordEncoder encoder;

//    @Override
//    public UserDTO getMyInfo(String username) {
//        User user = usersRepository.findByUsername(username);
//        if (user != null) {
//            return usersMapper.toUserDTO(user);
//        } else {
//            throw new NotFoundEntityException("User not found");
//        }
//    }

    @Override
    @Transactional
    public UserDTO updateUser(UpdateUserInfoDto updateUserInfoDto, String username) {

        User user = usersRepository.findByUsername(username);
        user.setFirstName(updateUserInfoDto.getFirstName());
        user.setLastName(updateUserInfoDto.getLastName());
        user.setPhone(updateUserInfoDto.getPhone());
        usersRepository.save(user);
        return usersMapper.toUserDTO(user);
    }

    @Override
    public boolean updateAvatar(String username, MultipartFile image) {
        User user = usersRepository.findByUsername(username);
        if (user != null) {
            user.setImage(imageService.addImage(image));
            usersRepository.save(user);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    @Transactional
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

    @Override
    public UserDTO getUser(String username) {

        User user = usersRepository.findByUsername(username);
        if (user != null) {

            return usersMapper.toUserDTO(user);
        } else {
            throw new NotFoundEntityException("00");
        }
    }
}

