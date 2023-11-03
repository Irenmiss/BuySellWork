package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object для процесса создания или редактирования объявления
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateAdDto {
    private String description;
    private Integer price;
    private String title;
}