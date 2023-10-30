package ru.skypro.homework.service.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.Enums.Role;
import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UsersRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.ValidationService;

import javax.validation.ValidationException;

import static ru.skypro.homework.Enums.Role.USER;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private PasswordEncoder encoder;
    private UsersRepository usersRepository;
    private ValidationService validationService;
    private UserDetailsService userDetailsService;
    private UserMapper userMapper;

    @Override
    public boolean login(String userName, String password) {

        User user = usersRepository.findByUsername(userName);
        if (user == null
                || !user.getUsername().equals(userName)
                && !user.getPassword().equals(encoder.encode(password))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterUserDto registerUserDto) {
        User user = usersRepository.findByUsername(registerUserDto.getUsername());
        if (user != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if (!validationService.validate(registerUserDto)) {
            throw new ValidationException(registerUserDto.toString());
        }

        try {
            Role role = registerUserDto.getRole() == null ? USER : registerUserDto.getRole();
            registerUserDto.setRole(role);
            registerUserDto.setPassword(encoder.encode(registerUserDto.getPassword()));
            User newUser = userMapper.toUserEntity(registerUserDto);
            usersRepository.save(newUser);
            return true;
        } catch (RuntimeException e) {
            e.getStackTrace();
            return false;
        }
    }
}
