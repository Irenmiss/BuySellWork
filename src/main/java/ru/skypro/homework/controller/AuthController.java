package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.Enums.Role;
import ru.skypro.homework.dto.LoginUserDto;
import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.service.AuthService;

import static ru.skypro.homework.Enums.Role.USER;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private AuthService authService;
    @Operation(
            summary = "Регистрация пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "OK"),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            })

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDto credentials) {

        if (authService.login(credentials.getUsername(), credentials.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
    @Operation(
            summary = "Регистрация пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Created"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request"
                    )
})
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto registerUserDto) {

        Role role = registerUserDto.getRole() == null ? USER : registerUserDto.getRole();

        if (authService.register(registerUserDto, role)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
