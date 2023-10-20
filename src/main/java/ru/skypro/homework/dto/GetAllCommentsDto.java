package ru.skypro.homework.dto;
import lombok.Data;

import java.util.List;

@Data
public class GetAllCommentsDto {
    private int count;
    private List<CommentsDto> results;
}
