package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.SetNewPasswordDto;
import ru.skypro.homework.dto.UpdateUserInfoDto;
import ru.skypro.homework.dto.UserDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {
    private UserService userService;
    @Operation(
            summary = "Обновление информации об авторизованном пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"

                    )
            })
    @PatchMapping("/me")
    public ResponseEntity<UpdateUserInfoDto> updateUser(@RequestBody UpdateUserInfoDto updateUserDto) {
        return ResponseEntity.ok(updateUserDto);
    }

    @Operation(
            summary = "Получение информации об авторизованном пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    )
            })
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUser() {
        return ResponseEntity.ok(new UserDTO());
    }

    @Operation(
            summary = "Обновление пароля",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"
                    ),
            })
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody SetNewPasswordDto newPasswordDto) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновление аватара авторизованного пользователя",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(responseCode = "401",
                            description = "Unauthorized"
                    ),
            })
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)

    public ResponseEntity<Void> updateUserAvatar(@RequestParam MultipartFile image,
                                                 Principal principal) throws IOException {
        userService.updateAvatar(principal.getName(), image);
        return ResponseEntity.ok().build();
    }
}
