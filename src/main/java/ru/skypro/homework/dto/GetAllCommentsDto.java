package ru.skypro.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object для просмотра всех комментариев к объявлению
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCommentsDto {
    private Integer count;
    private List<CommentsDto> results;
}
