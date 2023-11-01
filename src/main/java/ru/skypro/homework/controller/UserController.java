package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.SetNewPasswordDto;
import ru.skypro.homework.dto.UpdateUserInfoDto;
import ru.skypro.homework.dto.UserDTO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.security.Principal;


@RestController
@AllArgsConstructor
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {
    private UserService userService;
    private ImageService imageService;

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
    public ResponseEntity<UserDTO> updateUser(@RequestBody UpdateUserInfoDto updateUserDto, Principal principal) {
        try {
            UserDTO userDto = userService.updateUser(updateUserDto, principal.getName());
            return ResponseEntity.ok(userDto);
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
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
    public ResponseEntity<UserDTO> getUser(Principal principal) {

        try {
            UserDTO user = userService.getUser(principal.getName());
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
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
    public ResponseEntity<?> setPassword(@RequestBody SetNewPasswordDto password,
                                         Principal principal) {

        if (userService.setPassword(password, principal.getName())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping(value = "/image/{id}", produces = {
            MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.APPLICATION_OCTET_STREAM_VALUE,
            MediaType.IMAGE_GIF_VALUE
    })

    @Operation(summary = "Получить аватар пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    ),
            })
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {

        try {
            return ResponseEntity.ok(imageService.loadImage(id));
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
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
    public ResponseEntity<?> updateUserAvatar(@RequestPart(name = "image") MultipartFile image,
                                              Principal principal) {

        try {
            return ResponseEntity.ok().body(userService.updateAvatar(principal.getName(), image));
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}


