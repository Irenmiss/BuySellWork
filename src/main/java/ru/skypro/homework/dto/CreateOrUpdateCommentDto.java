package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object для процесса создания или редактирования комментария
 */
@Data
@AllArgsConstructor
public class CreateOrUpdateCommentDto {
    private String text;

    public CreateOrUpdateCommentDto() {
    }
}
