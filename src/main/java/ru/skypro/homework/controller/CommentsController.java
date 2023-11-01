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
    private CommentsService commentsService;

    @Operation(
            summary = "Добавление комментария к объявлению",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"
                    ),
                    @ApiResponse
                            (responseCode = "404",
                                    description = "Not found"
                            )
            })
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentsDto> saveComment(@PathVariable("id") Integer id,
                                                   @RequestBody CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                                   Principal principal) {
        try {
            CommentsDto comment = commentsService.createAdComment(id, createOrUpdateCommentDto, principal.getName());
            return ResponseEntity.ok(comment);
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Operation(
            summary = "Получение комментариев объявления",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Unauthorized"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not found"
                    )
            })
    @GetMapping("/{id}/comments")
    public ResponseEntity<GetAllCommentsDto> getAdComments(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(new GetAllCommentsDto());
    }

    @Operation(
            summary = "Обновление комментария",
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
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            })
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<CommentsDto> updateComment(@PathVariable Integer adId,
                                                     @PathVariable Integer commentId,
                                                     @RequestBody CommentsDto commentsDto,
                                                     Principal principal) {
        try {
            CommentsDto commentDto = commentsService.updateComment(adId, commentId, commentsDto, principal.getName());
            return ResponseEntity.ok(commentDto);
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Operation(
            summary = "Удаление комментария ",
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
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            })
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId,
                                           Principal principal) {
        try {
            return ResponseEntity.ok(commentsService.deleteCommentById(adId, commentId, principal.getName()));
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
