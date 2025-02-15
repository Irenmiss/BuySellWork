package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object для просмотра всех опубликованных объявлений
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllAdsDto {
    private Integer count;
    private List<AdsDto> results;

}
