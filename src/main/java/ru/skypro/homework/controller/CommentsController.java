package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("ads")

public class CommentsController {
    @Operation(
            summary = "Добавление комментария к объявлению "
    )
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentsDto> saveComment(@PathVariable("id") Integer id, @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto) {
        return ResponseEntity.ok(new CommentsDto());
    }
    @Operation(
            summary = "Получение комментариев объявления"
    )
    @GetMapping("/{id}/comments")
    public ResponseEntity<CommentsDto> getAdComments(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(new  CommentsDto());
    }

}
