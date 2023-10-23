package ru.skypro.homework.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.UserService;

import java.util.Optional;

@Service
@Data
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    @Override
    public void newPassword(String newPassword, String currentPassword) {
    }

    @Override
    public Optional<User> findUserByEmail(Authentication authentication) {
        return usersRepository.findByEmail(authentication.getName());
    }
    @Override
    public UserDTO updateUser(UserDTO newUserDto) {
        Optional<User> currentUser = findUser();
        User updatedtUser = new User();
        if (currentUser.isPresent()) {
            updatedtUser = currentUser.get();
            updatedtUser.setFirstName(newUserDto.getFirstName());
            updatedtUser.setLastName(newUserDto.getLastName());
            updatedtUser.setPhone(newUserDto.getPhone());
            usersRepository.save(updatedtUser);
        }
        return userMapper.toUserDTO(updatedtUser);
    }
    @Override
    public Optional <User> findUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return usersRepository.findByEmail(currentPrincipalName);
    }
}