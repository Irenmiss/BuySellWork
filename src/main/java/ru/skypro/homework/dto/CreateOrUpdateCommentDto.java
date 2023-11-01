package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateOrUpdateCommentDto {
    private String text;

    public CreateOrUpdateCommentDto() {
    }
}
