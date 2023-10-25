package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.GetAllCommentsDto;

import java.util.List;

public interface CommentsService {

    List<CommentsDto> getCommentsByIdAd(Integer id);
}
