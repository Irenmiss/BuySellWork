package ru.skypro.homework.dto;
import lombok.Data;

import java.util.List;

@Data
public class GetAllCommentsDto {
    private Integer count;
    private List<CommentsDto> results;
}
