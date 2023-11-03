package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object объявления
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdsDto {
    private Integer author;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;
}