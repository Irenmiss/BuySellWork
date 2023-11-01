package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.dto.GetAllCommentsDto;
import ru.skypro.homework.service.CommentsService;

import java.security.Principal;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("ads")

public class CommentsController {
    private final CommentsService commentsService;

    @PostMapping("/{id}/comments")
    @Operation(
            summary = "Добавление комментариев к объявлению",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            })
    public ResponseEntity<CommentsDto> addComment(@PathVariable("id") Integer id,
                                                  @RequestBody CreateOrUpdateCommentDto dto,
                                                  Principal principal) {

        try {
            CommentsDto comment = commentsService.createComment(id, dto, principal.getName());
            return ResponseEntity.ok(comment);
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PatchMapping("{adId}/comments/{commentId}")
    @Operation(
            summary = "Обновление комментария",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Forbidden"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            })
    public ResponseEntity<CommentsDto> updateComment(@PathVariable Integer adId,
                                                     @PathVariable Integer commentId,
                                                     @RequestBody CreateOrUpdateCommentDto comment,
                                                     Principal principal) {

        try {
            CommentsDto commentDto = commentsService.updateComment(adId, commentId, comment, principal.getName());
            return ResponseEntity.ok(commentDto);
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


    @DeleteMapping("/{adId}/comments/{commentId}")
    @Operation(
            summary = "Удаление комментарий",
            description = "нужно написать комментарий id и рекламу id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Удалось удалить комментарий"
    )
    @ApiResponse(
            responseCode = "401",
            description = "чтобы удалить комментарий необходимо авторизоваться"
    )
    @ApiResponse(
            responseCode = "403",
            description = "отсутствуют права доступа"
    )
    public ResponseEntity<?> deleteComment(@PathVariable Integer adId,
                                           @PathVariable Integer commentId,
                                           Principal principal) {
        try {
            return ResponseEntity.ok(commentsService.deleteComment(adId, commentId, principal.getName()));
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


    @GetMapping("/{id}/comments")
    @Operation(
            summary = "Получение комментариев объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            })
    public ResponseEntity<GetAllCommentsDto> getComments(@PathVariable("id") Integer id) {

        try {
            GetAllCommentsDto commentDto = commentsService.getComments(id);
            return ResponseEntity.ok(commentDto);
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
