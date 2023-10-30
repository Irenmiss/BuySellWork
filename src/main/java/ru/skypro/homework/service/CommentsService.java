package ru.skypro.homework.service;

import ru.skypro.homework.dto.CommentsDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;

import java.util.List;

public interface CommentsService {

    CommentsDto updateComment(Integer adId, Integer commentId, CreateOrUpdateCommentDto createOrUpdateCommentDto,
                              String userDetails);

    List<CommentsDto> getCommentByIdAd(Integer id);

    CommentsDto createAdComment(Integer id,
                                CreateOrUpdateCommentDto createOrUpdateCommentDto,
                                String userDetails);

    void deleteCommentById(Integer adId, Integer commentId, String userDetails);
}
