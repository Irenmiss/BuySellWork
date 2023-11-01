package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentsDto {
    private Integer author;
    private String authorImage;
    private String authorFirstName;
    private LocalDateTime createdAt;
    private Integer pk;
    private String text;
}
