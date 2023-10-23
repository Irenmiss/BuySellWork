package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.SetNewPasswordDto;
import ru.skypro.homework.dto.UpdateUserInfoDto;
import ru.skypro.homework.dto.UserDTO;
@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {
    @Operation(
            summary = "Обновление информации об авторизованном пользователе"
    )
    @PatchMapping("/me")
    public ResponseEntity<UpdateUserInfoDto> UpdateUser(@RequestBody UpdateUserInfoDto updateUserDto) {
        return ResponseEntity.ok(updateUserDto);
    }
    @Operation (
            summary = "Получение информации об авторизованном пользователе"
    )
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUser() {
        return ResponseEntity.ok(new UserDTO());
    }
    @Operation (
            summary = "Обновление пароля"
    )
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody SetNewPasswordDto newPasswordDto) {
        return ResponseEntity.ok().build();
    }
}
