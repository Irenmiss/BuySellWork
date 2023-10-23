package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.RegisterUserDto;
import ru.skypro.homework.service.AuthService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
    private final AuthService authService;
//    @PostMapping()
//    public ResponseEntity<?> register(@RequestBody RegisterUserDto register) {
//        if (authService.register(register)) {
//            return ResponseEntity.status(HttpStatus.CREATED).build();
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }

}
